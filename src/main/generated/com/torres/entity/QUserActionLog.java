package com.torres.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QUserActionLog is a Querydsl query type for UserActionLog
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QUserActionLog extends EntityPathBase<UserActionLog> {

    private static final long serialVersionUID = -272534352L;

    public static final QUserActionLog userActionLog = new QUserActionLog("userActionLog");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

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

