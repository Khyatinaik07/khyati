package utils.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.RelativeLayout;

import com.example.onlineserviceportal.R;
import com.google.android.material.button.MaterialButton;
import com.jakewharton.rxbinding3.widget.RxTextView;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ERSearchView extends RelativeLayout implements OnSearchQueryInputListener, View.OnClickListener {

    public static final int DEBOUNCE_TIMEOUT = 200;
    private OnSearchQueryInputListener onQueryInputListener;
    private ClearableEditText searchView;
    private MaterialButton btnCancelSearch;
    private Disposable disposable;

    public ERSearchView(Context context) {
        super(context);
        init();
    }

    public ERSearchView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ERSearchView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        View rootView = inflate(getContext(), R.layout.search_view_layout, this);
        btnCancelSearch = rootView.findViewById(R.id.btnCancelSearch);
        searchView = rootView.findViewById(R.id.edtSearchView);
        searchView.setOnQueryInputListener(this);
        btnCancelSearch.setOnClickListener(this);

        // Search view debounce implementation
        Observable<String> searchDebounce = RxTextView.textChanges(searchView)
                .filter(charSequence -> charSequence.length() > -1)
                .debounce(DEBOUNCE_TIMEOUT, TimeUnit.MILLISECONDS)
                .map(CharSequence::toString)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        disposable = searchDebounce.subscribe(query -> {
            if (onQueryInputListener != null) {
                onQueryInputListener.onTextEntered(query);
            }
        });
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }

    @Override
    public void onTextEntered(CharSequence query) {
        if (onQueryInputListener != null) {
            onQueryInputListener.onTextEntered(query);
        }
    }

    @Override
    public void onSearchCancelClick() {

    }

    public void setOnQueryInputListener(OnSearchQueryInputListener onQueryInputListener) {
        this.onQueryInputListener = onQueryInputListener;
    }

    public void clearText() {
        searchView.setText("");
    }

    public String getSearchQuery() {
        String searchString = searchView.getText().toString();
        return searchString == null ? "" : searchString;
    }

    public void setSearchVisibility(boolean visible) {
        int visibility = visible ? VISIBLE : GONE;

        // If same visibility applied again, don't do anything
        if (getVisibility() == visibility) {
            return;
        }

        // Open or Close keyboard based on visibility
        if (visible) {
            showKeyboard();
        } else {
            hideKeyboard();
        }

        setVisibility(visibility);
        clearText();
    }

    public void setAccentColor(int color) {
        btnCancelSearch.setTextColor(color);
    }

    @Override
    public void onClick(View v) {
        if (onQueryInputListener != null) {
            onQueryInputListener.onSearchCancelClick();
        }
    }

    public void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(getWindowToken(), 0);
        }
    }

    public void showKeyboard() {
        requestFocus();
        InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
        }
    }

    public ClearableEditText getInputText() {
        return searchView;
    }

}
