package com.torres.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserActionLog is a Querydsl query type for UserActionLog
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QUserActionLog extends EntityPathBase<UserActionLog> {

    private static final long serialVersionUID = -272534352L;

    public static final QUserActionLog userActionLog = new QUserActionLog("userActionLog");

    public final DateTimePath<java.util.Date> actionTime = createDateTime("actionTime", java.util.Date.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final SetPath<ServiceAccountLog, QServiceAccountLog> serviceAccountLogs = this.<ServiceAccountLog, QServiceAccountLog>createSet("serviceAccountLogs", ServiceAccountLog.class, QServiceAccountLog.class, PathInits.DIRECT2);

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public QUserActionLog(String variable) {
        super(UserActionLog.class, forVariable(variable));
    }

    public QUserActionLog(Path<? extends UserActionLog> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUserActionLog(PathMetadata metadata) {
        super(UserActionLog.class, metadata);
    }

}

