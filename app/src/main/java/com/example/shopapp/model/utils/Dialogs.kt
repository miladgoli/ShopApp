package com.example.shopapp.model.utils

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.location.GnssAntennaInfo
import android.view.View
import android.view.Window
import com.example.shopapp.R
import com.google.android.material.button.MaterialButton

object Dialogs {

    fun showDeleteCartDialog(context: Context, yesListener: View.OnClickListener) {

        val dialog = Dialog(context)

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_fragment_delete_product)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val yesBtn = dialog.findViewById(R.id.btnYesDialogDeleteProduct) as MaterialButton
        val noBtn = dialog.findViewById(R.id.btnNoDialogDeleteProduct) as MaterialButton

        yesBtn.setOnClickListener {

            yesListener.onClick(it)
            dialog.dismiss()

        }

        noBtn.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()

    }

    fun showDeleteAllCartsDialog(context: Context, yesListener: View.OnClickListener) {

        val dialog = Dialog(context)

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_fragment_delete_all_product)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val yesBtn = dialog.findViewById(R.id.btnYesDialogDeleteProduct) as MaterialButton
        val noBtn = dialog.findViewById(R.id.btnNoDialogDeleteProduct) as MaterialButton

        yesBtn.setOnClickListener {

            yesListener.onClick(it)
            dialog.dismiss()

        }

        noBtn.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()

    }

    fun showDeleteAllFavoriteDialog(context: Context, yesListener: View.OnClickListener) {

        val dialog = Dialog(context)

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_fragment_delete_all_favorite)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val yesBtn = dialog.findViewById(R.id.btnYesDialogDeleteProduct) as MaterialButton
        val noBtn = dialog.findViewById(R.id.btnNoDialogDeleteProduct) as MaterialButton

        yesBtn.setOnClickListener {

            yesListener.onClick(it)
            dialog.dismiss()

        }

        noBtn.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()

    }

    fun showSignoutDialog(context: Context, yesListener: View.OnClickListener) {

        val dialog = Dialog(context)

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_fragment_signout)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val yesBtn = dialog.findViewById(R.id.btnYesDialogDeleteProduct) as MaterialButton
        val noBtn = dialog.findViewById(R.id.btnNoDialogDeleteProduct) as MaterialButton

        yesBtn.setOnClickListener {

            yesListener.onClick(it)
            dialog.dismiss()

        }

        noBtn.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()

    }

    fun showSubmitEditedInfoDialog(context: Context, yesListener: View.OnClickListener,noListener: View.OnClickListener) {

        val dialog = Dialog(context)

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_fragment_submit_edit)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val yesBtn = dialog.findViewById(R.id.btnYesDialogDeleteProduct) as MaterialButton
        val noBtn = dialog.findViewById(R.id.btnNoDialogDeleteProduct) as MaterialButton

        yesBtn.setOnClickListener {
            yesListener.onClick(it)
            dialog.dismiss()
        }

        noBtn.setOnClickListener {
            noListener.onClick(it)
            dialog.dismiss()
        }

        dialog.show()

    }
}