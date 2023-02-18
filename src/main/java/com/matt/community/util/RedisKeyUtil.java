package com.matt.community.util;

public class RedisKeyUtil {

    private static final String SPLIT = ":";
    private static final String PRIVATE_ENTITY_LIKE = "like:entity";

    // 某个实体的赞
    // like:entity:entityType:entityId -> set(userId)
    public static String getEntityLikeKey(int entityType, int entityId) {
        return PRIVATE_ENTITY_LIKE + SPLIT + entityType + SPLIT + entityId;
    }

}
