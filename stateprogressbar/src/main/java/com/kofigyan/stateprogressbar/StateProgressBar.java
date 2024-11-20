package com.kofigyan.stateprogressbar.components;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class StateProgressBar extends View {

    // State Properties
    private List<String> stateList = new ArrayList<>();
    private int currentStateIndex = 0;

    // Paints
    private Paint activeStatePaint;
    private Paint inactiveStatePaint;
    private Paint textPaint;

    // Dimensions
    private int stateRadius = 40;
    private int stateSpacing = 150;
    private int centerX;
    private int centerY;

    // Listener
    private OnStateChangeListener stateChangeListener;

    // Theme Colors
    private int activeColor = Color.BLUE;
    private int inactiveColor = Color.GRAY;

    public StateProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        initializePaints();
    }

    private void initializePaints() {
        activeStatePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        activeStatePaint.setColor(activeColor);

        inactiveStatePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        inactiveStatePaint.setColor(inactiveColor);

        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setColor(Color.BLACK);
        textPaint.setTextSize(40);
        textPaint.setTextAlign(Paint.Align.CENTER);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        centerX = getWidth() / 2;
        centerY = stateRadius + 50;

        for (int i = 0; i < stateList.size(); i++) {
            Paint paint = (i <= currentStateIndex) ? activeStatePaint : inactiveStatePaint;
            canvas.drawCircle(centerX, centerY + (i * stateSpacing), stateRadius, paint);
            canvas.drawText(stateList.get(i), centerX, centerY + (i * stateSpacing) + stateRadius + 30, textPaint);
        }
    }

    public void setStates(List<String> states) {
        this.stateList = states;
        invalidate();
    }

    public void nextState() {
        if (currentStateIndex < stateList.size() - 1) {
            int oldIndex = currentStateIndex;
            currentStateIndex++;
            notifyStateChanged(oldIndex, currentStateIndex);
            invalidate();
        }
    }

    public void previousState() {
        if (currentStateIndex > 0) {
            int oldIndex = currentStateIndex;
            currentStateIndex--;
            notifyStateChanged(oldIndex, currentStateIndex);
            invalidate();
        }
    }

    public void addState(String stateName) {
        stateList.add(stateName);
        invalidate();
    }

    public void removeState(int position) {
        if (position >= 0 && position < stateList.size()) {
            stateList.remove(position);
            invalidate();
        }
    }

    public void applyTheme(Theme theme) {
        switch (theme) {
            case MINIMAL:
                activeColor = Color.BLACK;
                inactiveColor = Color.LIGHT_GRAY;
                break;
            case PLAYFUL:
                activeColor = Color.MAGENTA;
                inactiveColor = Color.YELLOW;
                break;
            case CORPORATE:
                activeColor = Color.BLUE;
                inactiveColor = Color.GRAY;
                break;
        }
        initializePaints();
        invalidate();
    }

    private void notifyStateChanged(int oldIndex, int newIndex) {
        if (stateChangeListener != null) {
            stateChangeListener.onStateChanged(oldIndex, newIndex);
        }
    }

    public void setOnStateChangeListener(OnStateChangeListener listener) {
        this.stateChangeListener = listener;
    }

    public interface OnStateChangeListener {
        void onStateChanged(int oldStateIndex, int newStateIndex);
    }

    public enum Theme {
        MINIMAL, PLAYFUL, CORPORATE
    }
}
