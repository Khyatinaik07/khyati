package utils.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.DrawableRes;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.core.content.ContextCompat;

import com.example.onlineserviceportal.R;

public class ClearableEditText extends AppCompatEditText implements TextWatcher {

    @DrawableRes
    private static final int DEFAULT_CLEAR_ICON_RES_ID = R.drawable.ic_clear;

    private Drawable mClearIconDrawable;

    private boolean mIsClearIconShown = false;

    private boolean mClearIconDrawWhenFocused = true;

    private OnTextClearedListener textClearedListener;
    private OnSearchQueryInputListener onQueryInputListener;

    public ClearableEditText(Context context) {
        this(context, null);
    }

    public ClearableEditText(Context context, AttributeSet attrs) {
        this(context, attrs, android.R.attr.editTextStyle);
    }

    public ClearableEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyle) {
        // Load attributes
        final TypedArray a =
                context.obtainStyledAttributes(attrs, R.styleable.ClearableEditText, defStyle, 0);

        if (a.hasValue(R.styleable.ClearableEditText_clearIconDrawable)) {
            mClearIconDrawable = a.getDrawable(R.styleable.ClearableEditText_clearIconDrawable);
        } else {
            mClearIconDrawable = ContextCompat.getDrawable(context, DEFAULT_CLEAR_ICON_RES_ID);
        }
        if (mClearIconDrawable != null) {
            mClearIconDrawable.setCallback(this);
        }

        mClearIconDrawWhenFocused =
                a.getBoolean(R.styleable.ClearableEditText_clearIconDrawWhenFocused, true);

        a.recycle();
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        // no operation
    }

    @Override
    public void afterTextChanged(Editable s) {
    }

    @Override
    public Parcelable onSaveInstanceState() {
        Parcelable superState = super.onSaveInstanceState();
        return mIsClearIconShown ? new ClearIconSavedState(superState, true) : superState;
    }

    @Override
    public void onRestoreInstanceState(Parcelable state) {
        if (!(state instanceof ClearIconSavedState)) {
            super.onRestoreInstanceState(state);
            return;
        }
        ClearIconSavedState savedState = (ClearIconSavedState) state;
        super.onRestoreInstanceState(savedState.getSuperState());
        mIsClearIconShown = savedState.isClearIconShown();
        showClearIcon(mIsClearIconShown);
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (!mClearIconDrawWhenFocused || hasFocus()) {
            showClearIcon(!TextUtils.isEmpty(s));
        }

        if (onQueryInputListener != null) {
            onQueryInputListener.onTextEntered(s);
        }
    }

    @Override
    protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
        showClearIcon((!mClearIconDrawWhenFocused || focused) && !TextUtils.isEmpty(getText()));
        super.onFocusChanged(focused, direction, previouslyFocusedRect);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (isClearIconTouched(event)) {
            setText(null);
            event.setAction(MotionEvent.ACTION_CANCEL);
            showClearIcon(false);
            if (textClearedListener != null) textClearedListener.onTextCleared();
            return false;
        }
        return super.onTouchEvent(event);
    }

    private boolean isClearIconTouched(MotionEvent event) {
        if (!mIsClearIconShown) {
            return false;
        }

        final int touchPointX = (int) event.getX();

        final int widthOfView = getWidth();
        final int compoundPaddingRight = getCompoundPaddingRight();

        return touchPointX >= widthOfView - compoundPaddingRight;
    }

    private void showClearIcon(boolean show) {
        Drawable[] drawables = getCompoundDrawables();

        if (show) {
            // show icon on the right
            setCompoundDrawablesWithIntrinsicBounds(
                    drawables[0], drawables[1], mClearIconDrawable, drawables[3]);
        } else {
            // remove icon
            setCompoundDrawables(drawables[0], drawables[1], null, drawables[3]);
        }
        mIsClearIconShown = show;
    }

    protected static class ClearIconSavedState extends BaseSavedState {

        public static final Creator<ClearIconSavedState> CREATOR =
                new Creator<ClearIconSavedState>() {
                    @Override
                    public ClearIconSavedState createFromParcel(Parcel source) {
                        return new ClearIconSavedState(source);
                    }

                    @Override
                    public ClearIconSavedState[] newArray(int size) {
                        return new ClearIconSavedState[size];
                    }
                };
        private final boolean mIsClearIconShown;

        private ClearIconSavedState(Parcel source) {
            super(source);
            mIsClearIconShown = source.readByte() != 0;
        }

        ClearIconSavedState(Parcelable superState, boolean isClearIconShown) {
            super(superState);
            mIsClearIconShown = isClearIconShown;
        }

        @Override
        public void writeToParcel(Parcel out, int flags) {
            super.writeToParcel(out, flags);
            out.writeByte((byte) (mIsClearIconShown ? 1 : 0));
        }

        boolean isClearIconShown() {
            return mIsClearIconShown;
        }
    }

    @SuppressWarnings("unused")
    public void setOnTextClearedListener(OnTextClearedListener textClearedListener) {
        this.textClearedListener = textClearedListener;
    }

    public void setOnQueryInputListener(OnSearchQueryInputListener onQueryInputListener) {
        this.onQueryInputListener = onQueryInputListener;
    }

}
