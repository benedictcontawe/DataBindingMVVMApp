package com.example.jetpackcomponentsapp

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.ListenableWorker
import androidx.work.WorkerParameters

class CompressingWorker : CoroutineWorker {

    companion object {
        private val TAG : String = CompressingWorker::class.java.getSimpleName()
    }

    constructor(context : Context, params : WorkerParameters) : super(context, params) {

    }

    override suspend fun doWork() : ListenableWorker.Result {
        return try {
            if (getRunAttemptCount() > 3) ListenableWorker.Result.failure()
            else {
                for (index : Int in 0..300) {
                    Log.d(TAG,"Compressing $index")
                }
                ListenableWorker.Result.success()
            }
        } catch (ex : Exception) {
            Log.e(TAG,"CompressingWorker doWork() Error ${ex.message}")
            ListenableWorker.Result.retry()
        }
    }
}