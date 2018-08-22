package company.home.intergrupoapp.utils.eventBus

interface EventBus {

    fun register(subscriber: Any)

    fun unregister(subscriber: Any)

    fun post(event: Any)

}