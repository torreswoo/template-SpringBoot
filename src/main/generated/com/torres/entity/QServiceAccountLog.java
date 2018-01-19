package com.torres.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QServiceAccountLog is a Querydsl query type for ServiceAccountLog
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QServiceAccountLog extends EntityPathBase<ServiceAccountLog> {

    private static final long serialVersionUID = -269762823L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QServiceAccountLog serviceAccountLog = new QServiceAccountLog("serviceAccountLog");

    public final StringPath accountNumber = createString("accountNumber");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final QUserActionLog userActionLog;

    public QServiceAccountLog(String variable) {
        this(ServiceAccountLog.class, forVariable(variable), INITS);
    }

    public QServiceAccountLog(Path<? extends ServiceAccountLog> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QServiceAccountLog(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QServiceAccountLog(PathMetadata metadata, PathInits inits) {
        this(ServiceAccountLog.class, metadata, inits);
    }

    public QServiceAccountLog(Class<? extends ServiceAccountLog> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.userActionLog = inits.isInitialized("userActionLog") ? new QUserActionLog(forProperty("userActionLog")) : null;
    }

}

