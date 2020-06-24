package hkucs.example.e_learningapp.ui.tasks.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Window;
import android.view.WindowManager;

import hkucs.example.e_learningapp.ui.tasks.model.TodoCallback;

abstract public class FullScreenDialog extends Dialog {

    protected TodoCallback callback;

    public FullScreenDialog(Context context, int layoutId) {
        super(context);

        setContentView(layoutId);

        // required to make the dialog use the full screen width
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        Window window = getWindow();
        lp.copyFrom(window.getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.horizontalMargin = 40;
        window.setAttributes(lp);
    }

    public void setDialogResult(TodoCallback resultCallback) {
        callback = resultCallback;
    }

}
