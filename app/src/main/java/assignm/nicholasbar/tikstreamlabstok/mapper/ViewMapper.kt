package assignm.nicholasbar.tikstreamlabstok.mapper

interface ViewMapper<V, D> {

    fun mapToView(type: D): V

}