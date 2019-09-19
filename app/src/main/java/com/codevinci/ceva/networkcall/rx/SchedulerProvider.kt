package com.codevinci.ceva.networkcall.rx

import io.reactivex.Scheduler

/**
 * Good for dependency injection and easy testing
 */
interface SchedulerProvider {
    fun ui(): Scheduler

    fun io(): Scheduler
}