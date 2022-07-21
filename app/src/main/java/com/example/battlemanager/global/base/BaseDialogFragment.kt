package com.example.battlemanager.global.base

import android.content.Context
import android.graphics.Color
import android.graphics.Point
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment

abstract class BaseDialogFragment<VB : ViewDataBinding> : DialogFragment() {
    protected lateinit var binding: VB
    abstract val layoutResourceId: Int

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, layoutResourceId, container, false)
        binding.lifecycleOwner = this

        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.window?.requestFeature(Window.FEATURE_NO_TITLE)
        return binding.root
    }

    open fun initState() {

    }

    open fun initDataBinding() {

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initState()
        initDataBinding()
    }

    fun Context.dialogFragmentResize(dialogFragment: DialogFragment, width: Float, height: Float) {
        val windowManager = getSystemService(Context.WINDOW_SERVICE) as WindowManager

        if (Build.VERSION.SDK_INT < 30) {

            val display = windowManager.defaultDisplay
            val size = Point()

            display.getSize(size)

            val window = dialogFragment.dialog?.window

            val x = (size.x * width).toInt()
            val y = (size.y * height).toInt()
            window?.setLayout(x, y)

        } else {

            val rect = windowManager.currentWindowMetrics.bounds

            val window = dialogFragment.dialog?.window

            val x = (rect.width() * width).toInt()
            val y = (rect.height() * height).toInt()

            window?.setLayout(x, y)
        }
    }
}