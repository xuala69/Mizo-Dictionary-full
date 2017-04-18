package k.realmdataentry;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Dsoloman on 31-03-2017.
 */

public class CustomizedText extends TextView {

    Context mContext;
    String customFont;


    public CustomizedText(Context context) {
        super(context);
        mContext = context;
        initialize(mContext,null);
    }

    public CustomizedText(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initialize(mContext,attrs);
    }


    public CustomizedText(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initialize(mContext,attrs);
    }

    private void initialize(Context context,AttributeSet attrs) {

        if (!isInEditMode()) {
            if (attrs!=null) {
                TypedArray typedArray=mContext.obtainStyledAttributes(attrs,R.styleable.CustomizedText);
                int fontType=typedArray.getInt(R.styleable.CustomizedText_font_type,0);
                switch (fontType) {
                    case 1:
                        fontType=R.string.roboto_bold;
                        break;
                    case 2:
                        fontType=R.string.roboto_italic;
                        break;
                    case 3:
                        fontType=R.string.roboto_light;
                        break;
                    case 4:
                        fontType=R.string.roboto_medium;
                        break;
                    case 5:
                        fontType=R.string.roboto_regular;
                        break;
                    case 6:
                        fontType=R.string.roboto_thin;
                        break;
                    default:
                        fontType=R.string.roboto_regular;
                        break;
                }
                customFont=getResources().getString(fontType);
                Typeface tf=Typeface.createFromAsset(context.getAssets(),"fonts/"+customFont+".ttf");
                setTypeface(tf);
                typedArray.recycle();

            }
        }
    }


}
