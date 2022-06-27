package com.seda.e_commerceappp.Worker

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.seda.e_commerceappp.Utils.NotificationHelper


class ShoppingWorker (val context: Context, val params: WorkerParameters) : Worker(context, params){
    override fun doWork(): Result {
        NotificationHelper(context).createNotification(
            inputData.getString("title").toString())

        return Result.success()
    }
}