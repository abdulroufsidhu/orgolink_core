package io.github.abdulroufsidhu.ambaar.apis.core.caching

import jakarta.servlet.http.HttpSession
import org.springframework.cache.Cache
import org.springframework.cache.CacheManager
import org.springframework.cache.concurrent.ConcurrentMapCache
import org.springframework.stereotype.Component
import java.util.concurrent.ConcurrentHashMap

@Component
class SessionAwareCacheManager(private val session: HttpSession) : CacheManager {

    private val cacheMap: MutableMap<String, Cache> = ConcurrentHashMap()

    override fun getCache(name: String): Cache? {
        val sessionId = session.id
        return cacheMap.computeIfAbsent("$sessionId-$name") { ConcurrentMapCache(it) }
    }

    override fun getCacheNames(): Collection<String> {
        return cacheMap.keys
    }
}
