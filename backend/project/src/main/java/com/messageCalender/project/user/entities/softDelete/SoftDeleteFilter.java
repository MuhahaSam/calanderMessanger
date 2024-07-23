package com.messageCalender.project.user.entities.softDelete;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.hibernate.annotations.SQLDelete;

import java.time.LocalDateTime;

@FilterDef(name = "softDeleteFilter", parameters = @ParamDef(name = "deletedAt", type = LocalDateTime.class))
@Filter(name = "softDeleteFilter", condition = "deleted_at IS NULL")
@SQLDelete(sql = "UPDATE {h-schema}{table-name} SET deleted_at = CURRENT_TIMESTAMP WHERE id = ?")
public @interface SoftDeleteFilter {
}