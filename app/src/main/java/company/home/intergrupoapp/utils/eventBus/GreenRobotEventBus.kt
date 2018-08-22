package company.home.intergrupoapp.utils.eventBus

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
        val instance: GreenRobotEventBus
            get() = SingletonHolder.INSTANCE
    }
}