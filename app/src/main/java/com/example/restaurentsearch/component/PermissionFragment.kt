package com.example.restaurentsearch.component

import android.annotation.TargetApi
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

class PermissionFragment : Fragment() {

    private var pendingJob: (() -> Unit)? = null
    private var pendingOnNotGranted: ((Boolean) -> Unit)? = null


    companion object {
        private const val PERMISSION_REQUEST_CODE = 22
        private const val TAG = "PermissionFragment"

        fun getPermissionFragment(activity: FragmentActivity): PermissionFragment {
            var fragment =
                activity.supportFragmentManager.findFragmentByTag(TAG) as PermissionFragment?
            if (fragment == null) {
                fragment = PermissionFragment()
                activity.supportFragmentManager.apply {
                    beginTransaction()
                        .add(fragment, TAG)
                        .commitAllowingStateLoss()
                    executePendingTransactions()
                }

            }
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    fun executeWithPermissionCheck(
        permission: String,
        job: () -> Unit,
        onNotGranted: (Boolean) -> Unit
    ) {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M || isGranted(permission)) {
            job.invoke()
        } else {
            pendingJob = job
            pendingOnNotGranted = onNotGranted
            requestPermissions(arrayOf(permission), PERMISSION_REQUEST_CODE)
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode != PERMISSION_REQUEST_CODE) {
            return
        }

        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            pendingJob?.invoke()
        } else {
            val neverAskAgain = !shouldShowRequestPermissionRationale(permissions[0])
            pendingOnNotGranted?.invoke(neverAskAgain)
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    private fun isGranted(permission: String): Boolean {
        return activity?.checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED
    }
}
