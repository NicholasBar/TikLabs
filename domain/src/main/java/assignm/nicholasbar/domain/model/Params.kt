package assignm.nicholasbar.domain.model

enum class TimeFrame(val value: String) {
    DAY("day"),
    WEEK("week"),
    MONTH("month"),
    YEAR("year"),
    ALL_TIME("all")
}

enum class Order(val value: String) {
    HOT("hot"),
    TOP("top"),
    NEW("new"),
    RANDOM("random")
}