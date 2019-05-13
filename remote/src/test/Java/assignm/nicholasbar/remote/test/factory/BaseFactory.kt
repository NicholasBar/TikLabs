package assignm.nicholasbar.remote.test.factory

import java.util.concurrent.ThreadLocalRandom

object BaseFactory {
    fun randomUuid(): String {
        return java.util.UUID.randomUUID().toString()
    }

    fun randomInt(): Int {
        return ThreadLocalRandom.current().nextInt(0, 1000 + 1)
    }

    fun randomBoolean(): Boolean {
        return Math.random() < 0.5
    }

    fun randomLong(): Long {
        return ThreadLocalRandom.current().nextLong(0, 1000 + 1)
    }
}