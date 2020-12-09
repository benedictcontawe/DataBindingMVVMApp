package com.example.jetpackcomponentsapp

import android.content.Context
import android.util.Log
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters

class CustomWorker : Worker {

    companion object {
        private val TAG : String = CustomWorker::class.java.getSimpleName()
    }

    constructor(context : Context, params : WorkerParameters) : super(context, params) {

    }

    override fun doWork() : Result {
        return try {
            if (getRunAttemptCount() > 3) Result.failure()
            else {
                val workerName : String = getInputData().getString(Constants.WORKER_NAME)?:"Nil"
                val workerInt : Int = getInputData().getInt(Constants.WORKER_INT,0)
                val workerString : String = getInputData().getString(Constants.WORKER_STRING)?:"Nil"
                val workerLong : Long = getInputData().getLong(Constants.WORKER_LONG,0L)
                for (index : Int in 0 until 100) {
                    Log.d(TAG,"Uploading $index")
                }
                Result.success(
                        Data.Builder()
                            .putString(Constants.WORKER_OUTPUT_NAME, "Worker Name $workerName ${MainViewModel.getDate()}")
                            .putString(Constants.WORKER_OUTPUT_VALUE, "Worker Values $workerInt $workerString $workerLong")
                            .build()
                )
            }
        } catch (ex : Exception) {
            Log.e(TAG,"CustomWorker doWork() Error ${ex.message}")
            Result.retry()
        }
    }
}