package dlh.fpt.custom;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

import dlh.fpt.utils.FontCache;

/**
 * Created by LOIBV on 8/1/2015.
 */
public class RobotoTextView extends TextView {

    private Typeface tf;

    public RobotoTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        changeFont();
    }

    public RobotoTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        changeFont();
    }

    public RobotoTextView(Context context) {
        super(context);
        changeFont();
    }

    private void changeFont() {
        if (!isInEditMode()) {
            setTypeface(FontCache.getFont(getContext(), "fonts/robotolight.ttf"));
        }
    }

}