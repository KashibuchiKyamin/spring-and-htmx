package com.kashibuchikyamin.htmxdemo.database.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.kashibuchikyamin.htmxdemo.database.entities.OrderRequestStatus;

@Mapper
public interface OrderRequestStatusMapper {

	@Select(value = """
			WITH base AS (
				SELECT
					ROWNUM() AS row_num
					,ulid
					,order_number
					,branch_number
					,reception_number
					,correction_number
					,project_name
					,customer_name
					,submission_date
					,delivery_date
					,status
					,create_user
					,create_datetime
					,update_user
					,update_datetime
				FROM
					order_request_status
				ORDER BY
					order_number DESC, branch_number DESC, ulid DESC
			)
				SELECT
					ulid
					,order_number
					,branch_number
					,reception_number
					,correction_number
					,project_name
					,customer_name
					,submission_date
					,delivery_date
					,status
					,create_user
					,create_datetime
					,update_user
					,update_datetime
				FROM
					base
				WHERE
					row_num >= ${pageNum}
				LIMIT ${limit}

						""", databaseId = "h2")
	List<OrderRequestStatus> get1ページ分のデータ(
			@Param("pageNum") int pageNum,
			@Param("limit") int limit);

	@Select(value = """
			SELECT
				COUNT(*)
			FROM
				order_request_status
					""", databaseId = "h2")
	int get対象データ全件数();
}
