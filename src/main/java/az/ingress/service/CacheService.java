package az.ingress.service;

import az.ingress.model.CacheData;
import az.ingress.util.CacheUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static java.time.temporal.ChronoUnit.MINUTES;

@Slf4j
@Service
@RequiredArgsConstructor
public class CacheService {

    private final CacheUtil cacheUtil;

    public void save(Long id) {
        var cacheKey = "ms-payment-service:payment-id:" + id;
        var data = new CacheData("Payment");
        cacheUtil.saveToCache(cacheKey, data, 1L, MINUTES);
    }

    public void get(String cacheKey) {
        var data = cacheUtil.getBucket(cacheKey);
        log.info("ActionLog.cacheData:{}", data);
    }
}
