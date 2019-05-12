package com.app.daniel.ifdoc.commons.distro

import android.app.Activity
import android.app.AlertDialog

import com.microsoft.appcenter.distribute.Distribute
import com.microsoft.appcenter.distribute.DistributeListener
import com.microsoft.appcenter.distribute.ReleaseDetails
import com.microsoft.appcenter.distribute.UpdateAction

class MyDistributeListener : DistributeListener {

    override fun onReleaseAvailable(activity: Activity, releaseDetails: ReleaseDetails): Boolean {

        val versionName = releaseDetails.shortVersion
        val releaseNotes = releaseDetails.releaseNotes

        val dialogBuilder = AlertDialog.Builder(activity)
        dialogBuilder.setTitle("Version $versionName is available!")
        dialogBuilder.setMessage(releaseNotes)

        dialogBuilder.setPositiveButton(com.microsoft.appcenter.distribute.R.string.appcenter_distribute_update_dialog_download) {
            dialog, which -> Distribute.notifyUpdateAction(UpdateAction.UPDATE)
        }

        if (!releaseDetails.isMandatoryUpdate) {
            dialogBuilder.setNegativeButton(com.microsoft.appcenter.distribute.R.string.appcenter_distribute_update_dialog_postpone) {
                dialog, which -> Distribute.notifyUpdateAction(UpdateAction.POSTPONE) }
        }
        dialogBuilder.setCancelable(true)
        dialogBuilder.create().show()

        return true
    }
}
