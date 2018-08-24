package company.home.intergrupoapp.utils.eventBus

import java.util.concurrent.atomic.AtomicBoolean

class GreenRobotEventBus private constructor() : EventBus {

    private val eventBus: org.greenrobot.eventbus.EventBus = org.greenrobot.eventbus.EventBus.getDefault()

    override fun register(subscriber: Any) {
        eventBus.register(subscriber)
    }

    override fun unregister(subscriber: Any) {
        eventBus.unregister(subscriber)
    }

    override fun post(event: Any) {
        eventBus.post(event)
    }

    private object SingletonHolder {
        internal val INSTANCE = GreenRobotEventBus()
    }

    companion object {
        private lateinit var INSTANCE: GreenRobotEventBus
        val instance: GreenRobotEventBus get() = INSTANCE
        private val initialized = AtomicBoolean()
        fun initialize() {
            if (!initialized.getAndSet(true)) {
                INSTANCE = GreenRobotEventBus()
            }
        }
    }

}