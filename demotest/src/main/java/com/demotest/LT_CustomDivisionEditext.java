package com.demotest;

import android.content.Context;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputConnectionWrapper;
import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author 李涛
 * @description
 * @Date 2017/11/15.
 */


public class LT_CustomDivisionEditext  extends EditText {

    private int maxLength = 18;
    private int contentType;
    private int start, count, before;


    public LT_CustomDivisionEditext(Context context) {
        super(context);
        addTextChangedListener(textWatcher);
    }

    public LT_CustomDivisionEditext(Context context, AttributeSet attrs) {
        super(context, attrs);
        addTextChangedListener(textWatcher);
    }

    public LT_CustomDivisionEditext(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        addTextChangedListener(textWatcher);
    }

    TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            Log.i("aaa","statr=="+start+"befor=="+before+"count=="+count);
            LT_CustomDivisionEditext.this.start = start;
            LT_CustomDivisionEditext.this.before = before;
            LT_CustomDivisionEditext.this.count = count;
        }

        @Override
        public void afterTextChanged(Editable s) {

            if (s == null) {
                return;
            }
            //判断是否是在中间输入，需要重新计算 true 表示在中间操作的  开始正常输入为false
            boolean isMiddle = (start + count) < (s.length());
            //在末尾输入时，是否需要加入空格
            boolean isNeedSpace = false;
            if (!isMiddle && isSpace(s.length())) {
                isNeedSpace = true;
            }

            if (isMiddle || isNeedSpace || count > 1) {

                Log.i("aaa","进来了");
                String newStr = s.toString();
                newStr = newStr.replace(" ", "");
                StringBuilder sb = new StringBuilder();
                int spaceCount = 0;
                for (int i = 0; i < newStr.length(); i++) {
                    sb.append(newStr.substring(i, i + 1)); //这里 是一个字一个字的添加
                    //如果当前输入的字符下一位为空格(i+1+1+spaceCount)，因为i是从0开始计算的，所以一开始的时候需要先加1（空格）
                    if (isSpace(i + 2 + spaceCount)) {             //5    6+3=9/    7 10/ 8  11/   9  12/   -------- 10  14/    11   15/   12   16/    13   17/
                        sb.append(" ");
                        spaceCount += 1;                                                    //2+0                                            2+1                                                                            2+2
                    }
                }

                removeTextChangedListener(textWatcher);
//                s.replace(0, s.length(), sb);
                s.clear();
                s.append(sb.toString());
                Log.i("bbb",""+sb.toString());
                //如果是在末尾的话,或者加入的字符个数大于零的话（输入或者粘贴）
                if (!isMiddle || count > 1) {
                    //setSelection(s.length() < maxLength ? s.length() : maxLength);
                } else if (isMiddle) {
                    //如果是删除
                    if (count == 0) {
                        //如果删除时，光标停留在空格的前面，光标则要往前移一位
                        if (isSpace(start - before + 1)) {
                            setSelection((start - before) > 0 ? start - before : 0);
                        } else {
                            setSelection((start - before + 1) > s.length() ? s.length() : (start - before + 1));
                        }
                    }
                    //如果是增加
                    else {
                        if (isSpace(start - before + count)) {
                            setSelection((start + count - before + 1) < s.length() ? (start + count - before + 1) : s.length());
                        } else {
                            setSelection(start + count - before);
                        }
                    }
                }
                addTextChangedListener(textWatcher);
            }
        }
    };



    private boolean isSpace(int length) {
       // return  isDoctorNum(length);
         return  isIDCardNum(length);
    }


    private boolean isDoctorNum(int length){
        if(length>25){
            Log.i("aaa","========>24");
            return length==26 ||(length - 2) % 5 == 0;
        }else if(length>16){
            Log.i("aaa","=====>16");
            return length>16&&length==17;
        }
        Log.i("aaa","=========-=====>9");
        return length > 9 && length == 10;
    }


    private boolean isIDCardNum(int length){
        if(length>15){
            return length==16 ||(length - 2) % 5 == 0;
        }
        return length > 6 && length == 7;
    }


    @Override
    public InputConnection onCreateInputConnection(EditorInfo outAttrs) {
        return new InnerInputConnection(super.onCreateInputConnection(outAttrs),false);
    }

    class InnerInputConnection extends InputConnectionWrapper implements InputConnection{


        /**
         * Initializes a wrapper.
         * <p>
         * <p><b>Caveat:</b> Although the system can accept {@code (InputConnection) null} in some
         * places, you cannot emulate such a behavior by non-null {@link InputConnectionWrapper} that
         * has {@code null} in {@code target}.</p>
         *
         * @param target  the {@link InputConnection} to be proxied.
         * @param mutable set {@code true} to protect this object from being reconfigured to target
         *                another {@link InputConnection}.  Note that this is ignored while the target is {@code null}.
         */
        public InnerInputConnection(InputConnection target, boolean mutable) {
            super(target, mutable);
        }

        @Override
        public boolean commitText(CharSequence text, int newCursorPosition) {

//            String regEx = "^[A-Za-z0-9]+$";  //只能输入字母和数字
            String regEx = "^[0-9]*$";  //只能输入自然数
            Pattern p = Pattern.compile(regEx);
            Matcher m = p.matcher(text);
            String str = m.replaceAll("").trim();
            if(!text.equals(str)||text.equals("*")){
                return super.commitText(text, newCursorPosition);
            }
          return false;
        }
    }
}

