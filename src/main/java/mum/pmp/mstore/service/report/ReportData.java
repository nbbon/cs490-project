/*
 * Author: Bon Nguyen
 * Date: 25-Apr-2019
 * Class Name: ReportData
 * Module: Report
 * Description: The entity class. Used by ReportRepository querying data from DB
 * 
 */

package mum.pmp.mstore.service.report;

import java.time.LocalDate;

import javax.persistence.*;

@Entity(name = "ReportData")
@SqlResultSetMappings({
	@SqlResultSetMapping(
        name="WeeklyReportDataMapping",
        classes=@ConstructorResult(
                targetClass=WeeklyReportData.class,
                columns={@ColumnResult(name="item_id", type=String.class),
                        @ColumnResult(name="item_name", type=String.class),
                        @ColumnResult(name="d1_sales", type=Integer.class),
                        @ColumnResult(name="d2_sales", type=Integer.class),
                        @ColumnResult(name="d3_sales", type=Integer.class),
                        @ColumnResult(name="d4_sales", type=Integer.class),
                        @ColumnResult(name="d5_sales", type=Integer.class),
                        @ColumnResult(name="d6_sales", type=Integer.class),
                        @ColumnResult(name="d7_sales", type=Integer.class),
                        @ColumnResult(name="total_sales", type=Integer.class),
                        @ColumnResult(name="total", type=Double.class)})),
	@SqlResultSetMapping(
	        name="WeeklyOverallReportDataMapping",
	        classes=@ConstructorResult(
	                targetClass=WeeklyOverallReportData.class,
	                columns={@ColumnResult(name="d1_sales", type=Integer.class),
	                        @ColumnResult(name="d2_sales", type=Integer.class),
	                        @ColumnResult(name="d3_sales", type=Integer.class),
	                        @ColumnResult(name="d4_sales", type=Integer.class),
	                        @ColumnResult(name="d5_sales", type=Integer.class),
	                        @ColumnResult(name="d6_sales", type=Integer.class),
	                        @ColumnResult(name="d7_sales", type=Integer.class),
	                        @ColumnResult(name="total_sales", type=Integer.class),
	                        @ColumnResult(name="total", type=Double.class)})),
	@SqlResultSetMapping(
	        name="MonthlyReportDataMapping",
	        classes=@ConstructorResult(
	        		targetClass=MonthlyReportData.class,
	                columns={@ColumnResult(name="item_id", type=String.class),
	                        @ColumnResult(name="item_name", type=String.class),
	                        @ColumnResult(name="m1_sales", type=Integer.class),
	                        @ColumnResult(name="m2_sales", type=Integer.class),
	                        @ColumnResult(name="m3_sales", type=Integer.class),
	                        @ColumnResult(name="m4_sales", type=Integer.class),
	                        @ColumnResult(name="m5_sales", type=Integer.class),
	                        @ColumnResult(name="m6_sales", type=Integer.class),
	                        @ColumnResult(name="m7_sales", type=Integer.class),
	                        @ColumnResult(name="m8_sales", type=Integer.class),
	                        @ColumnResult(name="m9_sales", type=Integer.class),
	                        @ColumnResult(name="m10_sales", type=Integer.class),
	                        @ColumnResult(name="m11_sales", type=Integer.class),
	                        @ColumnResult(name="m12_sales", type=Integer.class),
	                        @ColumnResult(name="total_sales", type=Integer.class),
	                        @ColumnResult(name="total", type=Double.class)})),
	@SqlResultSetMapping(
	        name="MonthlyOverallReportDataMapping",
	        classes=@ConstructorResult(
	        		targetClass=MonthlyOverallReportData.class,
	                columns={@ColumnResult(name="m1_sales", type=Integer.class),
	                        @ColumnResult(name="m2_sales", type=Integer.class),
	                        @ColumnResult(name="m3_sales", type=Integer.class),
	                        @ColumnResult(name="m4_sales", type=Integer.class),
	                        @ColumnResult(name="m5_sales", type=Integer.class),
	                        @ColumnResult(name="m6_sales", type=Integer.class),
	                        @ColumnResult(name="m7_sales", type=Integer.class),
	                        @ColumnResult(name="m8_sales", type=Integer.class),
	                        @ColumnResult(name="m9_sales", type=Integer.class),
	                        @ColumnResult(name="m10_sales", type=Integer.class),
	                        @ColumnResult(name="m11_sales", type=Integer.class),
	                        @ColumnResult(name="m12_sales", type=Integer.class),
	                        @ColumnResult(name="total_sales", type=Integer.class),
	                        @ColumnResult(name="total", type=Double.class)})),
	@SqlResultSetMapping(
	        name="QuarterlyReportDataMapping",
	        classes=@ConstructorResult(
	        		targetClass=QuarterlyReportData.class,
	                columns={@ColumnResult(name="item_id", type=String.class),
	                        @ColumnResult(name="item_name", type=String.class),
	                        @ColumnResult(name="q1_sales", type=Integer.class),
	                        @ColumnResult(name="q2_sales", type=Integer.class),
	                        @ColumnResult(name="q3_sales", type=Integer.class),
	                        @ColumnResult(name="q4_sales", type=Integer.class),
	                        @ColumnResult(name="total_sales", type=Integer.class),
	                        @ColumnResult(name="total", type=Double.class)})),
	@SqlResultSetMapping(
	        name="QuarterlyOverallReportDataMapping",
	        classes=@ConstructorResult(
	        		targetClass=QuarterlyOverallReportData.class,
	                columns={@ColumnResult(name="q1_sales", type=Integer.class),
	                        @ColumnResult(name="q2_sales", type=Integer.class),
	                        @ColumnResult(name="q3_sales", type=Integer.class),
	                        @ColumnResult(name="q4_sales", type=Integer.class),
	                        @ColumnResult(name="total_sales", type=Integer.class),
	                        @ColumnResult(name="total", type=Double.class)})),
	@SqlResultSetMapping(
	        name="HalfYearlyReportDataMapping",
	        classes=@ConstructorResult(
	        		targetClass=HalfYearlyReportData.class,
	                columns={@ColumnResult(name="item_id", type=String.class),
	                        @ColumnResult(name="item_name", type=String.class),
	                        @ColumnResult(name="fh_sales", type=Integer.class),
	                        @ColumnResult(name="sh_sales", type=Integer.class),
	                        @ColumnResult(name="total_sales", type=Integer.class),
	                        @ColumnResult(name="total", type=Double.class)})),
	@SqlResultSetMapping(
	        name="HalfYearlyOverallReportDataMapping",
	        classes=@ConstructorResult(
	        		targetClass=HalfYearlyOverallReportData.class,
	                columns={@ColumnResult(name="fh_sales", type=Integer.class),
	                        @ColumnResult(name="sh_sales", type=Integer.class),
	                        @ColumnResult(name="total_sales", type=Integer.class),
	                        @ColumnResult(name="total", type=Double.class)})),
	@SqlResultSetMapping(
	        name="AnnuallyReportDataMapping",
	        classes=@ConstructorResult(
	        		targetClass=AnnuallyReportData.class,
	                columns={@ColumnResult(name="item_id", type=String.class),
	                        @ColumnResult(name="item_name", type=String.class),
	                        @ColumnResult(name="year_sales", type=Integer.class),
	                        @ColumnResult(name="total_sales", type=Integer.class),
	                        @ColumnResult(name="total", type=Double.class)})),
	@SqlResultSetMapping(
	        name="AnnuallyOverallReportDataMapping",
	        classes=@ConstructorResult(
	        		targetClass=AnnuallyOverallReportData.class,
	                columns={@ColumnResult(name="year_sales", type=Integer.class),
	                        @ColumnResult(name="total_sales", type=Integer.class),
	                        @ColumnResult(name="total", type=Double.class)})),
	@SqlResultSetMapping(
	        name="AdhocReportDataMapping",
	        classes=@ConstructorResult(
	        		targetClass=AdhocReportData.class,
	                columns={@ColumnResult(name="item_id", type=String.class),
	                        @ColumnResult(name="item_name", type=String.class),
	                        @ColumnResult(name="range_sales", type=LocalDate.class),
	                        @ColumnResult(name="total_sales", type=Integer.class),
	                        @ColumnResult(name="total", type=Double.class)})),
	@SqlResultSetMapping(
	        name="AdhocOverallReportDataMapping",
	        classes=@ConstructorResult(
	        		targetClass=AdhocOverallReportData.class,
	                columns={@ColumnResult(name="range_sales", type=LocalDate.class),
	                        @ColumnResult(name="total_sales", type=Integer.class),
	                        @ColumnResult(name="total", type=Double.class)})),
})
@NamedNativeQueries({
	@NamedNativeQuery(
        name="ReportData.weeklySalesByProduct",
        query="SELECT \n" + 
        		"	w.product_number AS item_id,\n" + 
        		"    w.product_name AS item_name,\n" + 
        		"    sum(w.d1) AS d1_sales,\n" + 
        		"    sum(w.d2) AS d2_sales,\n" + 
        		"    sum(w.d3) AS d3_sales,\n" + 
        		"    sum(w.d4) AS d4_sales,\n" + 
        		"    sum(w.d5) AS d5_sales,\n" + 
        		"    sum(w.d6) AS d6_sales,\n" + 
        		"    sum(w.d7) AS d7_sales,\n" + 
        		"    sum(w.d1 + w.d2 + w.d3 + w.d4 + w.d5 + w.d6 + w.d7) as total_sales, \n" + 
        		"    sum(w.total) as total  \n" + 
        		"FROM (SELECT \n" + 
        		"    op.product_number,\n" + 
        		"    op.product_name,\n" + 
        		"    op.d1,\n" + 
        		"    0 AS d2,\n" + 
        		"    0 AS d3,\n" + 
        		"    0 AS d4,\n" + 
        		"    0 AS d5,\n" + 
        		"    0 AS d6,\n" + 
        		"    0 AS d7,\n" + 
        		"    op.total \n" + 
        		"FROM\n" + 
        		"    mstore_db.orders o\n" + 
        		"        LEFT JOIN\n" + 
        		"    (SELECT \n" + 
        		"        ol.order_number,\n" + 
        		"            ol.product_number,\n" + 
        		"            p.product_name,\n" + 
        		"            SUM(ol.quantity) AS d1,\n" + 
        		"            ol.quantity*p.price AS total \n" + 
        		"    FROM\n" + 
        		"        mstore_db.order_line ol\n" + 
        		"    LEFT JOIN mstore_db.product p ON ol.product_number = p.id\n" + 
        		"    GROUP BY ol.order_number , ol.product_number , p.product_name, total) op ON o.order_number = op.order_number \n" + 
        		"WHERE\n" + 
        		"    o.order_date = :d1 \n" + 
        		"UNION SELECT \n" + 
        		"    op.product_number,\n" + 
        		"    op.product_name,\n" + 
        		"    0 AS d1,\n" + 
        		"    op.d2,\n" + 
        		"    0 AS d3,\n" + 
        		"    0 AS d4,\n" + 
        		"    0 AS d5,\n" + 
        		"    0 AS d6,\n" + 
        		"    0 AS d7,\n" + 
        		"    op.total \n" + 
        		"FROM\n" + 
        		"    mstore_db.orders o\n" + 
        		"        LEFT JOIN\n" + 
        		"    (SELECT \n" + 
        		"        ol.order_number,\n" + 
        		"            ol.product_number,\n" + 
        		"            p.product_name,\n" + 
        		"            SUM(ol.quantity) AS d2,\n" + 
        		"            ol.quantity*p.price AS total \n" + 
        		"    FROM\n" + 
        		"        mstore_db.order_line ol\n" + 
        		"    LEFT JOIN mstore_db.product p ON ol.product_number = p.id\n" + 
        		"    GROUP BY ol.order_number , ol.product_number , p.product_name, total) op ON o.order_number = op.order_number \n" + 
        		"WHERE\n" + 
        		"    o.order_date = :d2 \n" + 
        		"UNION SELECT \n" + 
        		"    op.product_number,\n" + 
        		"    op.product_name,\n" + 
        		"    0 AS d1,\n" + 
        		"    0 AS d2,\n" + 
        		"    op.d3,\n" + 
        		"    0 AS d4,\n" + 
        		"    0 AS d5,\n" + 
        		"    0 AS d6,\n" + 
        		"    0 AS d7,\n" + 
        		"    op.total \n" + 
        		"FROM\n" + 
        		"    mstore_db.orders o\n" + 
        		"        LEFT JOIN\n" + 
        		"    (SELECT \n" + 
        		"        ol.order_number,\n" + 
        		"            ol.product_number,\n" + 
        		"            p.product_name,\n" + 
        		"            SUM(ol.quantity) AS d3,\n" + 
        		"            ol.quantity*p.price AS total \n" + 
        		"    FROM\n" + 
        		"        mstore_db.order_line ol\n" + 
        		"    LEFT JOIN mstore_db.product p ON ol.product_number = p.id\n" + 
        		"    GROUP BY ol.order_number , ol.product_number , p.product_name, total) op ON o.order_number = op.order_number \n" + 
        		"WHERE\n" + 
        		"    o.order_date = :d3 \n" + 
        		"UNION SELECT \n" + 
        		"    op.product_number,\n" + 
        		"    op.product_name,\n" + 
        		"    0 AS d1,\n" + 
        		"    0 AS d2,\n" + 
        		"    0 AS d3,\n" + 
        		"    op.d4,\n" + 
        		"    0 AS d5,\n" + 
        		"    0 AS d6,\n" + 
        		"    0 AS d7,\n" + 
        		"    op.total \n" + 
        		"FROM\n" + 
        		"    mstore_db.orders o\n" + 
        		"        LEFT JOIN\n" + 
        		"    (SELECT \n" + 
        		"        ol.order_number,\n" + 
        		"            ol.product_number,\n" + 
        		"            p.product_name,\n" + 
        		"            SUM(ol.quantity) AS d4,\n" + 
        		"            ol.quantity*p.price AS total \n" + 
        		"    FROM\n" + 
        		"        mstore_db.order_line ol\n" + 
        		"    LEFT JOIN mstore_db.product p ON ol.product_number = p.id\n" + 
        		"    GROUP BY ol.order_number , ol.product_number , p.product_name, total) op ON o.order_number = op.order_number \n" + 
        		"WHERE\n" + 
        		"    o.order_date = :d4 \n" + 
        		"UNION SELECT \n" + 
        		"    op.product_number,\n" + 
        		"    op.product_name,\n" + 
        		"    0 AS d1,\n" + 
        		"    0 AS d2,\n" + 
        		"    0 AS d3,\n" + 
        		"    0 AS d4,\n" + 
        		"    op.d5,\n" + 
        		"    0 AS d6,\n" + 
        		"    0 AS d7,\n" + 
        		"    op.total \n" + 
        		"FROM\n" + 
        		"    mstore_db.orders o\n" + 
        		"        LEFT JOIN\n" + 
        		"    (SELECT \n" + 
        		"        ol.order_number,\n" + 
        		"            ol.product_number,\n" + 
        		"            p.product_name,\n" + 
        		"            SUM(ol.quantity) AS d5,\n" + 
        		"            ol.quantity*p.price AS total \n" + 
        		"    FROM\n" + 
        		"        mstore_db.order_line ol\n" + 
        		"    LEFT JOIN mstore_db.product p ON ol.product_number = p.id\n" + 
        		"    GROUP BY ol.order_number , ol.product_number , p.product_name, total) op ON o.order_number = op.order_number \n" + 
        		"WHERE\n" + 
        		"    o.order_date = :d5 \n" + 
        		"UNION SELECT \n" + 
        		"    op.product_number,\n" + 
        		"    op.product_name,\n" + 
        		"    0 AS d1,\n" + 
        		"    0 AS d2,\n" + 
        		"    0 AS d3,\n" + 
        		"    0 AS d4,\n" + 
        		"    0 AS d5,\n" + 
        		"    op.d6,\n" + 
        		"    0 AS d7,\n" + 
        		"    op.total \n" + 
        		"FROM\n" + 
        		"    mstore_db.orders o\n" + 
        		"        LEFT JOIN\n" + 
        		"    (SELECT \n" + 
        		"        ol.order_number,\n" + 
        		"            ol.product_number,\n" + 
        		"            p.product_name,\n" + 
        		"            SUM(ol.quantity) AS d6,\n" + 
        		"            ol.quantity*p.price AS total \n" + 
        		"    FROM\n" + 
        		"        mstore_db.order_line ol\n" + 
        		"    LEFT JOIN mstore_db.product p ON ol.product_number = p.id\n" + 
        		"    GROUP BY ol.order_number , ol.product_number , p.product_name, total) op ON o.order_number = op.order_number \n" + 
        		"WHERE\n" + 
        		"    o.order_date = :d6 \n" + 
        		"UNION SELECT \n" + 
        		"    op.product_number,\n" + 
        		"    op.product_name,\n" + 
        		"    0 AS d1,\n" + 
        		"    0 AS d2,\n" + 
        		"    0 AS d3,\n" + 
        		"    0 AS d4,\n" + 
        		"    0 AS d5,\n" + 
        		"    0 AS d6,\n" + 
        		"    op.d7,\n" + 
        		"    op.total \n" + 
        		"FROM\n" + 
        		"    mstore_db.orders o\n" + 
        		"        LEFT JOIN\n" + 
        		"    (SELECT \n" + 
        		"        ol.order_number,\n" + 
        		"            ol.product_number,\n" + 
        		"            p.product_name,\n" + 
        		"            SUM(ol.quantity) AS d7,\n" + 
        		"            ol.quantity*p.price AS total \n" + 
        		"    FROM\n" + 
        		"        mstore_db.order_line ol\n" + 
        		"    LEFT JOIN mstore_db.product p ON ol.product_number = p.id\n" + 
        		"    GROUP BY ol.order_number , ol.product_number , p.product_name, total) op ON o.order_number = op.order_number \n" + 
        		"WHERE\n" + 
        		"    o.order_date = :d7) AS w\n" + 
        		"GROUP BY item_id , item_name",
        resultSetMapping="WeeklyReportDataMapping"),
	@NamedNativeQuery(
	        name="ReportData.weeklySalesByCategory",
	        query="SELECT \n" + 
	        		"	w.category_id AS item_id,\n" + 
	        		"    w.category_name AS item_name,\n" + 
	        		"    sum(w.d1) AS d1_sales,\n" + 
	        		"    sum(w.d2) AS d2_sales,\n" + 
	        		"    sum(w.d3) AS d3_sales,\n" + 
	        		"    sum(w.d4) AS d4_sales,\n" + 
	        		"    sum(w.d5) AS d5_sales,\n" + 
	        		"    sum(w.d6) AS d6_sales,\n" + 
	        		"    sum(w.d7) AS d7_sales,\n" + 
	        		"    sum(w.d1 + w.d2 + w.d3 + w.d4 + w.d5 + w.d6 + w.d7) as total_sales,\n" + 
	        		"    sum(w.total) AS total \n" + 
	        		"FROM (SELECT \n" + 
	        		"    op.category_id,\n" + 
	        		"    op.category_name,\n" + 
	        		"    op.d1,\n" + 
	        		"    0 AS d2,\n" + 
	        		"    0 AS d3,\n" + 
	        		"    0 AS d4,\n" + 
	        		"    0 AS d5,\n" + 
	        		"    0 AS d6,\n" + 
	        		"    0 AS d7,\n" + 
	        		"    op.total  \n" + 
	        		"FROM\n" + 
	        		"    mstore_db.orders o\n" + 
	        		"        LEFT JOIN\n" + 
	        		"    (SELECT \n" + 
	        		"        ol.order_number,\n" + 
	        		"            pc.category_id,\n" + 
	        		"            pc.category_name,\n" + 
	        		"            SUM(ol.quantity) AS d1,\n" + 
	        		"            sum(ol.quantity*pc.price) AS total \n" + 
	        		"    FROM\n" + 
	        		"        mstore_db.order_line ol\n" + 
	        		"    LEFT JOIN (SELECT \n" + 
	        		"        p.category_id, c.category_name, p.price, p.id\n" + 
	        		"    FROM\n" + 
	        		"        mstore_db.product p\n" + 
	        		"    LEFT JOIN mstore_db.category c ON c.categoryid = p.category_id) pc ON ol.product_number = pc.id\n" + 
	        		"    GROUP BY ol.order_number , pc.category_id , pc.category_name) op ON o.order_number = op.order_number \n" + 
	        		"WHERE\n" + 
	        		"    o.order_date = :d1  \n" + 
	        		"UNION SELECT \n" + 
	        		"    op.category_id,\n" + 
	        		"    op.category_name,\n" + 
	        		"    0 AS d1,\n" + 
	        		"    op.d2,\n" + 
	        		"    0 AS d3,\n" + 
	        		"    0 AS d4,\n" + 
	        		"    0 AS d5,\n" + 
	        		"    0 AS d6,\n" + 
	        		"    0 AS d7,\n" + 
	        		"    op.total \n" + 
	        		"FROM\n" + 
	        		"    mstore_db.orders o\n" + 
	        		"        LEFT JOIN\n" + 
	        		"    (SELECT \n" + 
	        		"        ol.order_number,\n" + 
	        		"            pc.category_id,\n" + 
	        		"            pc.category_name,\n" + 
	        		"            SUM(ol.quantity) AS d2,\n" + 
	        		"            sum(ol.quantity*pc.price) AS total \n" + 
	        		"    FROM\n" + 
	        		"        mstore_db.order_line ol\n" + 
	        		"    LEFT JOIN (SELECT \n" + 
	        		"        p.category_id, c.category_name, p.price, p.id\n" + 
	        		"    FROM\n" + 
	        		"        mstore_db.product p\n" + 
	        		"    LEFT JOIN mstore_db.category c ON c.categoryid = p.category_id) pc ON ol.product_number = pc.id\n" + 
	        		"    GROUP BY ol.order_number , pc.category_id , pc.category_name) op ON o.order_number = op.order_number \n" + 
	        		"WHERE\n" + 
	        		"    o.order_date = :d2 \n" + 
	        		"UNION SELECT \n" + 
	        		"    op.category_id,\n" + 
	        		"    op.category_name,\n" + 
	        		"    0 AS d1,\n" + 
	        		"    0 AS d2,\n" + 
	        		"    op.d3,\n" + 
	        		"    0 AS d4,\n" + 
	        		"    0 AS d5,\n" + 
	        		"    0 AS d6,\n" + 
	        		"    0 AS d7,\n" + 
	        		"    op.total \n" + 
	        		"FROM\n" + 
	        		"    mstore_db.orders o\n" + 
	        		"        LEFT JOIN\n" + 
	        		"    (SELECT \n" + 
	        		"        ol.order_number,\n" + 
	        		"            pc.category_id,\n" + 
	        		"            pc.category_name,\n" + 
	        		"            SUM(ol.quantity) AS d3,\n" + 
	        		"            sum(ol.quantity*pc.price) AS total \n" + 
	        		"    FROM\n" + 
	        		"        mstore_db.order_line ol\n" + 
	        		"    LEFT JOIN (SELECT \n" + 
	        		"        p.category_id, c.category_name, p.price, p.id\n" + 
	        		"    FROM\n" + 
	        		"        mstore_db.product p\n" + 
	        		"    LEFT JOIN mstore_db.category c ON c.categoryid = p.category_id) pc ON ol.product_number = pc.id\n" + 
	        		"    GROUP BY ol.order_number , pc.category_id , pc.category_name) op ON o.order_number = op.order_number \n" + 
	        		"WHERE\n" + 
	        		"    o.order_date = :d3 \n" + 
	        		"UNION SELECT \n" + 
	        		"    op.category_id,\n" + 
	        		"    op.category_name,\n" + 
	        		"    0 AS d1,\n" + 
	        		"    0 AS d2,\n" + 
	        		"    0 AS d3,\n" + 
	        		"    op.d4,\n" + 
	        		"    0 AS d5,\n" + 
	        		"    0 AS d6,\n" + 
	        		"    0 AS d7,\n" + 
	        		"    op.total \n" + 
	        		"FROM\n" + 
	        		"    mstore_db.orders o\n" + 
	        		"        LEFT JOIN\n" + 
	        		"    (SELECT \n" + 
	        		"        ol.order_number,\n" + 
	        		"            pc.category_id,\n" + 
	        		"            pc.category_name,\n" + 
	        		"            SUM(ol.quantity) AS d4,\n" + 
	        		"            sum(ol.quantity*pc.price) AS total \n" + 
	        		"    FROM\n" + 
	        		"        mstore_db.order_line ol\n" + 
	        		"    LEFT JOIN (SELECT \n" + 
	        		"        p.category_id, c.category_name, p.price, p.id\n" + 
	        		"    FROM\n" + 
	        		"        mstore_db.product p\n" + 
	        		"    LEFT JOIN mstore_db.category c ON c.categoryid = p.category_id) pc ON ol.product_number = pc.id\n" + 
	        		"    GROUP BY ol.order_number , pc.category_id , pc.category_name) op ON o.order_number = op.order_number \n" + 
	        		"WHERE\n" + 
	        		"    o.order_date = :d4 \n" + 
	        		"UNION SELECT \n" + 
	        		"    op.category_id,\n" + 
	        		"    op.category_name,\n" + 
	        		"    0 AS d1,\n" + 
	        		"    0 AS d2,\n" + 
	        		"    0 AS d3,\n" + 
	        		"    0 AS d4,\n" + 
	        		"    op.d5,\n" + 
	        		"    0 AS d6,\n" + 
	        		"    0 AS d7,\n" + 
	        		"    op.total \n" + 
	        		"FROM\n" + 
	        		"    mstore_db.orders o\n" + 
	        		"        LEFT JOIN\n" + 
	        		"    (SELECT \n" + 
	        		"        ol.order_number,\n" + 
	        		"            pc.category_id,\n" + 
	        		"            pc.category_name,\n" + 
	        		"            SUM(ol.quantity) AS d5,\n" + 
	        		"            sum(ol.quantity*pc.price) AS total \n" + 
	        		"    FROM\n" + 
	        		"        mstore_db.order_line ol\n" + 
	        		"    LEFT JOIN (SELECT \n" + 
	        		"        p.category_id, c.category_name, p.price, p.id\n" + 
	        		"    FROM\n" + 
	        		"        mstore_db.product p\n" + 
	        		"    LEFT JOIN mstore_db.category c ON c.categoryid = p.category_id) pc ON ol.product_number = pc.id\n" + 
	        		"    GROUP BY ol.order_number , pc.category_id , pc.category_name) op ON o.order_number = op.order_number \n" + 
	        		"WHERE\n" + 
	        		"    o.order_date = :d5  \n" + 
	        		"UNION SELECT \n" + 
	        		"    op.category_id,\n" + 
	        		"    op.category_name,\n" + 
	        		"    0 AS d1,\n" + 
	        		"    0 AS d2,\n" + 
	        		"    0 AS d3,\n" + 
	        		"    0 AS d4,\n" + 
	        		"    0 AS d5,\n" + 
	        		"    op.d6,\n" + 
	        		"    0 AS d7,\n" + 
	        		"    op.total \n" + 
	        		"FROM\n" + 
	        		"    mstore_db.orders o\n" + 
	        		"        LEFT JOIN\n" + 
	        		"    (SELECT \n" + 
	        		"        ol.order_number,\n" + 
	        		"            pc.category_id,\n" + 
	        		"            pc.category_name,\n" + 
	        		"            SUM(ol.quantity) AS d6,\n" + 
	        		"            sum(ol.quantity*pc.price) AS total  \n" + 
	        		"    FROM\n" + 
	        		"        mstore_db.order_line ol\n" + 
	        		"    LEFT JOIN (SELECT \n" + 
	        		"        p.category_id, c.category_name, p.price, p.id\n" + 
	        		"    FROM\n" + 
	        		"        mstore_db.product p\n" + 
	        		"    LEFT JOIN mstore_db.category c ON c.categoryid = p.category_id) pc ON ol.product_number = pc.id\n" + 
	        		"    GROUP BY ol.order_number , pc.category_id , pc.category_name) op ON o.order_number = op.order_number \n" + 
	        		"WHERE\n" + 
	        		"    o.order_date = :d6 \n" + 
	        		"UNION SELECT \n" + 
	        		"    op.category_id,\n" + 
	        		"    op.category_name,\n" + 
	        		"    0 AS d1,\n" + 
	        		"    0 AS d2,\n" + 
	        		"    0 AS d3,\n" + 
	        		"    0 AS d4,\n" + 
	        		"    0 AS d5,\n" + 
	        		"    0 AS d6,\n" + 
	        		"    op.d7,\n" + 
	        		"    op.total \n" + 
	        		"FROM\n" + 
	        		"    mstore_db.orders o\n" + 
	        		"        LEFT JOIN\n" + 
	        		"    (SELECT \n" + 
	        		"        ol.order_number,\n" + 
	        		"            pc.category_id,\n" + 
	        		"            pc.category_name,\n" + 
	        		"            SUM(ol.quantity) AS d7,\n" + 
	        		"            sum(ol.quantity*pc.price) AS total \n" + 
	        		"    FROM\n" + 
	        		"        mstore_db.order_line ol\n" + 
	        		"    LEFT JOIN (SELECT \n" + 
	        		"        p.category_id, c.category_name, p.price, p.id\n" + 
	        		"    FROM\n" + 
	        		"        mstore_db.product p\n" + 
	        		"    LEFT JOIN mstore_db.category c ON c.categoryid = p.category_id) pc ON ol.product_number = pc.id\n" + 
	        		"    GROUP BY ol.order_number , pc.category_id , pc.category_name) op ON o.order_number = op.order_number \n" + 
	        		"WHERE\n" + 
	        		"    o.order_date = :d7) AS w\n" + 
	        		"GROUP BY item_id , item_name",
	        resultSetMapping="WeeklyReportDataMapping"),
	@NamedNativeQuery(
	        name="ReportData.weeklyOverallSales",
	        query="SELECT \n" + 
	        		"	sum(w.d1) AS d1_sales,\n" + 
	        		"    sum(w.d2) AS d2_sales,\n" + 
	        		"    sum(w.d3) AS d3_sales,\n" + 
	        		"    sum(w.d4) AS d4_sales,\n" + 
	        		"    sum(w.d5) AS d5_sales,\n" + 
	        		"    sum(w.d6) AS d6_sales,\n" + 
	        		"    sum(w.d7) AS d7_sales,\n" + 
	        		"    sum(w.d1 + w.d2 + w.d3 + w.d4 + w.d5 + w.d6 + w.d7) as total_sales,\n" + 
	        		"    sum(w.total) AS total \n" + 
	        		"FROM (SELECT \n" + 
	        		"    sum(op.d1) AS d1,\n" + 
	        		"    0 AS d2,\n" + 
	        		"    0 AS d3,\n" + 
	        		"    0 AS d4,\n" + 
	        		"    0 AS d5,\n" + 
	        		"    0 AS d6,\n" + 
	        		"    0 AS d7,\n" + 
	        		"    sum(op.total) AS total   \n" + 
	        		"FROM\n" + 
	        		"    mstore_db.orders o\n" + 
	        		"        LEFT JOIN\n" + 
	        		"    (SELECT \n" + 
	        		"        ol.order_number,\n" + 
	        		"            SUM(ol.quantity) AS d1,\n" + 
	        		"            sum(ol.quantity*pc.price) AS total \n" + 
	        		"    FROM\n" + 
	        		"        mstore_db.order_line ol\n" + 
	        		"    LEFT JOIN (SELECT \n" + 
	        		"        p.category_id, c.category_name, p.price, p.id\n" + 
	        		"    FROM\n" + 
	        		"        mstore_db.product p\n" + 
	        		"    LEFT JOIN mstore_db.category c ON c.categoryid = p.category_id) pc ON ol.product_number = pc.id\n" + 
	        		"    GROUP BY ol.order_number) op ON o.order_number = op.order_number \n" + 
	        		"WHERE\n" + 
	        		"    o.order_date = :d1  \n" + 
	        		"UNION SELECT \n" + 
	        		"    0 AS d1,\n" + 
	        		"    sum(op.d2) AS d2,\n" + 
	        		"    0 AS d3,\n" + 
	        		"    0 AS d4,\n" + 
	        		"    0 AS d5,\n" + 
	        		"    0 AS d6,\n" + 
	        		"    0 AS d7,\n" + 
	        		"    sum(op.total) AS total  \n" + 
	        		"FROM\n" + 
	        		"    mstore_db.orders o\n" + 
	        		"        LEFT JOIN\n" + 
	        		"    (SELECT \n" + 
	        		"        ol.order_number,\n" + 
	        		"            SUM(ol.quantity) AS d2,\n" + 
	        		"            sum(ol.quantity*pc.price) AS total \n" + 
	        		"    FROM\n" + 
	        		"        mstore_db.order_line ol\n" + 
	        		"    LEFT JOIN (SELECT \n" + 
	        		"        p.category_id, c.category_name, p.price, p.id\n" + 
	        		"    FROM\n" + 
	        		"        mstore_db.product p\n" + 
	        		"    LEFT JOIN mstore_db.category c ON c.categoryid = p.category_id) pc ON ol.product_number = pc.id\n" + 
	        		"    GROUP BY ol.order_number) op ON o.order_number = op.order_number \n" + 
	        		"WHERE\n" + 
	        		"    o.order_date = :d2 \n" + 
	        		"UNION SELECT \n" + 
	        		"    0 AS d1,\n" + 
	        		"    0 AS d2,\n" + 
	        		"    sum(op.d3) AS d3,\n" + 
	        		"    0 AS d4,\n" + 
	        		"    0 AS d5,\n" + 
	        		"    0 AS d6,\n" + 
	        		"    0 AS d7,\n" + 
	        		"    sum(op.total) AS total  \n" + 
	        		"FROM\n" + 
	        		"    mstore_db.orders o\n" + 
	        		"        LEFT JOIN\n" + 
	        		"    (SELECT \n" + 
	        		"        ol.order_number,\n" + 
	        		"            SUM(ol.quantity) AS d3,\n" + 
	        		"            sum(ol.quantity*pc.price) AS total \n" + 
	        		"    FROM\n" + 
	        		"        mstore_db.order_line ol\n" + 
	        		"    LEFT JOIN (SELECT \n" + 
	        		"        p.category_id, c.category_name, p.price, p.id\n" + 
	        		"    FROM\n" + 
	        		"        mstore_db.product p\n" + 
	        		"    LEFT JOIN mstore_db.category c ON c.categoryid = p.category_id) pc ON ol.product_number = pc.id\n" + 
	        		"    GROUP BY ol.order_number) op ON o.order_number = op.order_number \n" + 
	        		"WHERE\n" + 
	        		"    o.order_date = :d3 \n" + 
	        		"UNION SELECT \n" + 
	        		"    0 AS d1,\n" + 
	        		"    0 AS d2,\n" + 
	        		"    0 AS d3,\n" + 
	        		"    sum(op.d4) AS d4,\n" + 
	        		"    0 AS d5,\n" + 
	        		"    0 AS d6,\n" + 
	        		"    0 AS d7,\n" + 
	        		"    sum(op.total) AS total  \n" + 
	        		"FROM\n" + 
	        		"    mstore_db.orders o\n" + 
	        		"        LEFT JOIN\n" + 
	        		"    (SELECT \n" + 
	        		"        ol.order_number,\n" + 
	        		"            SUM(ol.quantity) AS d4,\n" + 
	        		"            sum(ol.quantity*pc.price) AS total \n" + 
	        		"    FROM\n" + 
	        		"        mstore_db.order_line ol\n" + 
	        		"    LEFT JOIN (SELECT \n" + 
	        		"        p.category_id, c.category_name, p.price, p.id\n" + 
	        		"    FROM\n" + 
	        		"        mstore_db.product p\n" + 
	        		"    LEFT JOIN mstore_db.category c ON c.categoryid = p.category_id) pc ON ol.product_number = pc.id\n" + 
	        		"    GROUP BY ol.order_number) op ON o.order_number = op.order_number \n" + 
	        		"WHERE\n" + 
	        		"    o.order_date = :d4 \n" + 
	        		"UNION SELECT \n" + 
	        		"    0 AS d1,\n" + 
	        		"    0 AS d2,\n" + 
	        		"    0 AS d3,\n" + 
	        		"    0 AS d4,\n" + 
	        		"    sum(op.d5) AS d5,\n" + 
	        		"    0 AS d6,\n" + 
	        		"    0 AS d7,\n" + 
	        		"    sum(op.total) AS total \n" + 
	        		"FROM\n" + 
	        		"    mstore_db.orders o\n" + 
	        		"        LEFT JOIN\n" + 
	        		"    (SELECT \n" + 
	        		"        ol.order_number,\n" + 
	        		"            SUM(ol.quantity) AS d5,\n" + 
	        		"            sum(ol.quantity*pc.price) AS total \n" + 
	        		"    FROM\n" + 
	        		"        mstore_db.order_line ol\n" + 
	        		"    LEFT JOIN (SELECT \n" + 
	        		"        p.category_id, c.category_name, p.price, p.id\n" + 
	        		"    FROM\n" + 
	        		"        mstore_db.product p\n" + 
	        		"    LEFT JOIN mstore_db.category c ON c.categoryid = p.category_id) pc ON ol.product_number = pc.id\n" + 
	        		"    GROUP BY ol.order_number) op ON o.order_number = op.order_number \n" + 
	        		"WHERE\n" + 
	        		"    o.order_date = :d5  \n" + 
	        		"UNION SELECT \n" + 
	        		"    0 AS d1,\n" + 
	        		"    0 AS d2,\n" + 
	        		"    0 AS d3,\n" + 
	        		"    0 AS d4,\n" + 
	        		"    0 AS d5,\n" + 
	        		"    sum(op.d6) AS d6,\n" + 
	        		"    0 AS d7,\n" + 
	        		"    sum(op.total) AS total \n" + 
	        		"FROM\n" + 
	        		"    mstore_db.orders o\n" + 
	        		"        LEFT JOIN\n" + 
	        		"    (SELECT \n" + 
	        		"        ol.order_number,\n" + 
	        		"            SUM(ol.quantity) AS d6,\n" + 
	        		"            sum(ol.quantity*pc.price) AS total  \n" + 
	        		"    FROM\n" + 
	        		"        mstore_db.order_line ol\n" + 
	        		"    LEFT JOIN (SELECT \n" + 
	        		"        p.category_id, c.category_name, p.price, p.id\n" + 
	        		"    FROM\n" + 
	        		"        mstore_db.product p\n" + 
	        		"    LEFT JOIN mstore_db.category c ON c.categoryid = p.category_id) pc ON ol.product_number = pc.id\n" + 
	        		"    GROUP BY ol.order_number) op ON o.order_number = op.order_number \n" + 
	        		"WHERE\n" + 
	        		"    o.order_date = :d6 \n" + 
	        		"UNION SELECT \n" + 
	        		"    0 AS d1,\n" + 
	        		"    0 AS d2,\n" + 
	        		"    0 AS d3,\n" + 
	        		"    0 AS d4,\n" + 
	        		"    0 AS d5,\n" + 
	        		"    0 AS d6,\n" + 
	        		"    sum(op.d7) AS d7,\n" + 
	        		"    sum(op.total) AS total \n" + 
	        		"FROM\n" + 
	        		"    mstore_db.orders o\n" + 
	        		"        LEFT JOIN\n" + 
	        		"    (SELECT \n" + 
	        		"        ol.order_number,\n" + 
	        		"            SUM(ol.quantity) AS d7,\n" + 
	        		"            sum(ol.quantity*pc.price) AS total \n" + 
	        		"    FROM\n" + 
	        		"        mstore_db.order_line ol\n" + 
	        		"    LEFT JOIN (SELECT \n" + 
	        		"        p.category_id, c.category_name, p.price, p.id\n" + 
	        		"    FROM\n" + 
	        		"        mstore_db.product p\n" + 
	        		"    LEFT JOIN mstore_db.category c ON c.categoryid = p.category_id) pc ON ol.product_number = pc.id\n" + 
	        		"    GROUP BY ol.order_number) op ON o.order_number = op.order_number \n" + 
	        		"WHERE\n" + 
	        		"    o.order_date = :d7) AS w",
	        resultSetMapping="WeeklyOverallReportDataMapping"),
	@NamedNativeQuery(
	        name="ReportData.monthlySalesByProduct",
	        query="SELECT \n" + 
	        		"	w.product_number AS item_id,\n" + 
	        		"    w.product_name AS item_name,\n" + 
	        		"    sum(w.m1) AS m1_sales,\n" + 
	        		"    sum(w.m2) AS m2_sales,\n" + 
	        		"    sum(w.m3) AS m3_sales,\n" + 
	        		"    sum(w.m4) AS m4_sales,\n" + 
	        		"    sum(w.m5) AS m5_sales,\n" + 
	        		"    sum(w.m6) AS m6_sales,\n" + 
	        		"    sum(w.m7) AS m7_sales,\n" + 
	        		"    sum(w.m7) AS m8_sales,\n" + 
	        		"    sum(w.m7) AS m9_sales,\n" + 
	        		"    sum(w.m7) AS m10_sales,\n" + 
	        		"    sum(w.m7) AS m11_sales,\n" + 
	        		"    sum(w.m7) AS m12_sales,\n" + 
	        		"    sum(w.m1 + w.m2 + w.m3 + w.m4 + w.m5 + w.m6 + w.m7 + w.m8 + w.m9 + w.m10 + w.m11 + w.m12) as total_sales, \n" + 
	        		"    sum(w.total) as total   \n" + 
	        		"FROM (SELECT \n" + 
	        		"    op.product_number,\n" + 
	        		"    op.product_name,\n" + 
	        		"    sum(op.m1) AS m1,\n" + 
	        		"    0 AS m2,\n" + 
	        		"    0 AS m3,\n" + 
	        		"    0 AS m4,\n" + 
	        		"    0 AS m5,\n" + 
	        		"    0 AS m6,\n" + 
	        		"    0 AS m7,\n" + 
	        		"    0 AS m8,\n" + 
	        		"    0 AS m9,\n" + 
	        		"    0 AS m10,\n" + 
	        		"    0 AS m11,\n" + 
	        		"    0 AS m12,\n" + 
	        		"    sum(op.total) AS total \n" + 
	        		"FROM\n" + 
	        		"    mstore_db.orders o\n" + 
	        		"        LEFT JOIN\n" + 
	        		"    (SELECT \n" + 
	        		"        ol.order_number,\n" + 
	        		"            ol.product_number,\n" + 
	        		"            p.product_name,\n" + 
	        		"            SUM(ol.quantity) AS m1,\n" + 
	        		"            sum(ol.quantity)*p.price AS total \n" + 
	        		"    FROM\n" + 
	        		"        mstore_db.order_line ol\n" + 
	        		"    LEFT JOIN mstore_db.product p ON ol.product_number = p.id\n" + 
	        		"    GROUP BY ol.order_number , ol.product_number , p.product_name) op ON o.order_number = op.order_number \n" + 
	        		"WHERE\n" + 
	        		"    YEAR(o.order_date) = :r_year AND MONTH(o.order_date) = 1 \n" + 
	        		"    GROUP BY op.product_number , op.product_name, op.total, op.m1\n" + 
	        		"UNION SELECT \n" + 
	        		"    op.product_number,\n" + 
	        		"    op.product_name,\n" + 
	        		"    0 AS m1,\n" + 
	        		"    sum(op.m2) AS m2,\n" + 
	        		"    0 AS m3,\n" + 
	        		"    0 AS m4,\n" + 
	        		"    0 AS m5,\n" + 
	        		"    0 AS m6,\n" + 
	        		"    0 AS m7,\n" + 
	        		"    0 AS m8,\n" + 
	        		"    0 AS m9,\n" + 
	        		"    0 AS m10,\n" + 
	        		"    0 AS m11,\n" + 
	        		"    0 AS m12,\n" + 
	        		"    sum(op.total) AS total \n" + 
	        		"FROM\n" + 
	        		"    mstore_db.orders o\n" + 
	        		"        LEFT JOIN\n" + 
	        		"    (SELECT \n" + 
	        		"        ol.order_number,\n" + 
	        		"            ol.product_number,\n" + 
	        		"            p.product_name,\n" + 
	        		"            SUM(ol.quantity) AS m2,\n" + 
	        		"            sum(ol.quantity)*p.price AS total \n" + 
	        		"    FROM\n" + 
	        		"        mstore_db.order_line ol\n" + 
	        		"    LEFT JOIN mstore_db.product p ON ol.product_number = p.id\n" + 
	        		"    GROUP BY ol.order_number , ol.product_number , p.product_name) op ON o.order_number = op.order_number \n" + 
	        		"WHERE\n" + 
	        		"    YEAR(o.order_date) = :r_year AND MONTH(o.order_date) = 2\n" + 
	        		"    GROUP BY op.product_number , op.product_name, op.total, op.m2\n" + 
	        		"UNION SELECT \n" + 
	        		"    op.product_number,\n" + 
	        		"    op.product_name,\n" + 
	        		"    0 AS m1,\n" + 
	        		"    0 AS m2,\n" + 
	        		"    sum(op.m3) AS m3,\n" + 
	        		"    0 AS m4,\n" + 
	        		"    0 AS m5,\n" + 
	        		"    0 AS m6,\n" + 
	        		"    0 AS m7,\n" + 
	        		"    0 AS m8,\n" + 
	        		"    0 AS m9,\n" + 
	        		"    0 AS m10,\n" + 
	        		"    0 AS m11,\n" + 
	        		"    0 AS m12,\n" + 
	        		"    sum(op.total) AS total \n" + 
	        		"FROM\n" + 
	        		"    mstore_db.orders o\n" + 
	        		"        LEFT JOIN\n" + 
	        		"    (SELECT \n" + 
	        		"        ol.order_number,\n" + 
	        		"            ol.product_number,\n" + 
	        		"            p.product_name,\n" + 
	        		"            SUM(ol.quantity) AS m3,\n" + 
	        		"            sum(ol.quantity)*p.price AS total \n" + 
	        		"    FROM\n" + 
	        		"        mstore_db.order_line ol\n" + 
	        		"    LEFT JOIN mstore_db.product p ON ol.product_number = p.id\n" + 
	        		"    GROUP BY ol.order_number , ol.product_number , p.product_name) op ON o.order_number = op.order_number \n" + 
	        		"WHERE\n" + 
	        		"    YEAR(o.order_date) = :r_year AND MONTH(o.order_date) = 3\n" + 
	        		"    GROUP BY op.product_number , op.product_name, op.total, op.m3\n" + 
	        		"UNION SELECT \n" + 
	        		"    op.product_number,\n" + 
	        		"    op.product_name,\n" + 
	        		"    0 AS m1,\n" + 
	        		"    0 AS m2,\n" + 
	        		"    0 AS m3,\n" + 
	        		"    sum(op.m4) AS m4,\n" + 
	        		"    0 AS m5,\n" + 
	        		"    0 AS m6,\n" + 
	        		"    0 AS m7,\n" + 
	        		"    0 AS m8,\n" + 
	        		"    0 AS m9,\n" + 
	        		"    0 AS m10,\n" + 
	        		"    0 AS m11,\n" + 
	        		"    0 AS m12,\n" + 
	        		"    sum(op.total) AS total \n" + 
	        		"FROM\n" + 
	        		"    mstore_db.orders o\n" + 
	        		"        LEFT JOIN\n" + 
	        		"    (SELECT \n" + 
	        		"        ol.order_number,\n" + 
	        		"            ol.product_number,\n" + 
	        		"            p.product_name,\n" + 
	        		"            SUM(ol.quantity) AS m4,\n" + 
	        		"            sum(ol.quantity)*p.price AS total \n" + 
	        		"    FROM\n" + 
	        		"        mstore_db.order_line ol\n" + 
	        		"    LEFT JOIN mstore_db.product p ON ol.product_number = p.id\n" + 
	        		"    GROUP BY ol.order_number , ol.product_number , p.product_name) op ON o.order_number = op.order_number \n" + 
	        		"WHERE\n" + 
	        		"    YEAR(o.order_date) = :r_year AND MONTH(o.order_date) = 4\n" + 
	        		"    GROUP BY op.product_number , op.product_name, op.total, op.m4\n" + 
	        		"UNION SELECT \n" + 
	        		"    op.product_number,\n" + 
	        		"    op.product_name,\n" + 
	        		"    0 AS m1,\n" + 
	        		"    0 AS m2,\n" + 
	        		"    0 AS m3,\n" + 
	        		"    0 AS m4,\n" + 
	        		"    sum(op.m5) AS m5,\n" + 
	        		"    0 AS m6,\n" + 
	        		"    0 AS m7,\n" + 
	        		"    0 AS m8,\n" + 
	        		"    0 AS m9,\n" + 
	        		"    0 AS m10,\n" + 
	        		"    0 AS m11,\n" + 
	        		"    0 AS m12,\n" + 
	        		"    sum(op.total) AS total \n" + 
	        		"FROM\n" + 
	        		"    mstore_db.orders o\n" + 
	        		"        LEFT JOIN\n" + 
	        		"    (SELECT \n" + 
	        		"        ol.order_number,\n" + 
	        		"            ol.product_number,\n" + 
	        		"            p.product_name,\n" + 
	        		"            SUM(ol.quantity) AS m5,\n" + 
	        		"            sum(ol.quantity)*p.price AS total \n" + 
	        		"    FROM\n" + 
	        		"        mstore_db.order_line ol\n" + 
	        		"    LEFT JOIN mstore_db.product p ON ol.product_number = p.id\n" + 
	        		"    GROUP BY ol.order_number , ol.product_number , p.product_name) op ON o.order_number = op.order_number \n" + 
	        		"WHERE\n" + 
	        		"    YEAR(o.order_date) = :r_year AND MONTH(o.order_date) = 5\n" + 
	        		"    GROUP BY op.product_number , op.product_name, op.total, op.m5\n" + 
	        		"UNION SELECT \n" + 
	        		"    op.product_number,\n" + 
	        		"    op.product_name,\n" + 
	        		"    0 AS m1,\n" + 
	        		"    0 AS m2,\n" + 
	        		"    0 AS m3,\n" + 
	        		"    0 AS m4,\n" + 
	        		"    0 AS m5,\n" + 
	        		"    sum(op.m6) AS m6,\n" + 
	        		"    0 AS m7,\n" + 
	        		"    0 AS m8,\n" + 
	        		"    0 AS m9,\n" + 
	        		"    0 AS m10,\n" + 
	        		"    0 AS m11,\n" + 
	        		"    0 AS m12,\n" + 
	        		"    sum(op.total) AS total \n" + 
	        		"FROM\n" + 
	        		"    mstore_db.orders o\n" + 
	        		"        LEFT JOIN\n" + 
	        		"    (SELECT \n" + 
	        		"        ol.order_number,\n" + 
	        		"            ol.product_number,\n" + 
	        		"            p.product_name,\n" + 
	        		"            SUM(ol.quantity) AS m6,\n" + 
	        		"            sum(ol.quantity)*p.price AS total \n" + 
	        		"    FROM\n" + 
	        		"        mstore_db.order_line ol\n" + 
	        		"    LEFT JOIN mstore_db.product p ON ol.product_number = p.id\n" + 
	        		"    GROUP BY ol.order_number , ol.product_number , p.product_name) op ON o.order_number = op.order_number \n" + 
	        		"WHERE\n" + 
	        		"    YEAR(o.order_date) = :r_year AND MONTH(o.order_date) = 6  \n" + 
	        		"    GROUP BY op.product_number , op.product_name, op.total, op.m6\n" + 
	        		"UNION SELECT \n" + 
	        		"    op.product_number,\n" + 
	        		"    op.product_name,\n" + 
	        		"    0 AS m1,\n" + 
	        		"    0 AS m2,\n" + 
	        		"    0 AS m3,\n" + 
	        		"    0 AS m4,\n" + 
	        		"    0 AS m5,\n" + 
	        		"    0 AS m6,\n" + 
	        		"    sum(op.m7) AS m7,\n" + 
	        		"    0 AS m8,\n" + 
	        		"    0 AS m9,\n" + 
	        		"    0 AS m10,\n" + 
	        		"    0 AS m11,\n" + 
	        		"    0 AS m12,\n" + 
	        		"    sum(op.total) AS total \n" + 
	        		"FROM\n" + 
	        		"    mstore_db.orders o\n" + 
	        		"        LEFT JOIN\n" + 
	        		"    (SELECT \n" + 
	        		"        ol.order_number,\n" + 
	        		"            ol.product_number,\n" + 
	        		"            p.product_name,\n" + 
	        		"            SUM(ol.quantity) AS m7,\n" + 
	        		"            sum(ol.quantity)*p.price AS total \n" + 
	        		"    FROM\n" + 
	        		"        mstore_db.order_line ol\n" + 
	        		"    LEFT JOIN mstore_db.product p ON ol.product_number = p.id\n" + 
	        		"    GROUP BY ol.order_number , ol.product_number , p.product_name) op ON o.order_number = op.order_number \n" + 
	        		"WHERE\n" + 
	        		"    YEAR(o.order_date) = :r_year AND MONTH(o.order_date) = 7\n" + 
	        		"	GROUP BY op.product_number , op.product_name, op.total, op.m7\n" + 
	        		"UNION SELECT \n" + 
	        		"    op.product_number,\n" + 
	        		"    op.product_name,\n" + 
	        		"    0 AS m1,\n" + 
	        		"    0 AS m2,\n" + 
	        		"    0 AS m3,\n" + 
	        		"    0 AS m4,\n" + 
	        		"    0 AS m5,\n" + 
	        		"    0 AS m6,\n" + 
	        		"    0 AS m7,\n" + 
	        		"    sum(op.m8) AS m8,\n" + 
	        		"    0 AS m9,\n" + 
	        		"    0 AS m10,\n" + 
	        		"    0 AS m11,\n" + 
	        		"    0 AS m12,\n" + 
	        		"    sum(op.total) AS total \n" + 
	        		"FROM\n" + 
	        		"    mstore_db.orders o\n" + 
	        		"        LEFT JOIN\n" + 
	        		"    (SELECT \n" + 
	        		"        ol.order_number,\n" + 
	        		"            ol.product_number,\n" + 
	        		"            p.product_name,\n" + 
	        		"            SUM(ol.quantity) AS m8,\n" + 
	        		"            sum(ol.quantity)*p.price AS total \n" + 
	        		"    FROM\n" + 
	        		"        mstore_db.order_line ol\n" + 
	        		"    LEFT JOIN mstore_db.product p ON ol.product_number = p.id\n" + 
	        		"    GROUP BY ol.order_number , ol.product_number , p.product_name) op ON o.order_number = op.order_number \n" + 
	        		"WHERE\n" + 
	        		"    YEAR(o.order_date) = :r_year AND MONTH(o.order_date) = 8\n" + 
	        		"	GROUP BY op.product_number , op.product_name, op.total, op.m8\n" + 
	        		"    UNION SELECT \n" + 
	        		"    op.product_number,\n" + 
	        		"    op.product_name,\n" + 
	        		"    0 AS m1,\n" + 
	        		"    0 AS m2,\n" + 
	        		"    0 AS m3,\n" + 
	        		"    0 AS m4,\n" + 
	        		"    0 AS m5,\n" + 
	        		"    0 AS m6,\n" + 
	        		"    0 AS m7,\n" + 
	        		"    0 AS m8,\n" + 
	        		"    sum(op.m9) AS m9,\n" + 
	        		"    0 AS m10,\n" + 
	        		"    0 AS m11,\n" + 
	        		"    0 AS m12,\n" + 
	        		"    sum(op.total) AS total \n" + 
	        		"FROM\n" + 
	        		"    mstore_db.orders o\n" + 
	        		"        LEFT JOIN\n" + 
	        		"    (SELECT \n" + 
	        		"        ol.order_number,\n" + 
	        		"            ol.product_number,\n" + 
	        		"            p.product_name,\n" + 
	        		"            SUM(ol.quantity) AS m9,\n" + 
	        		"            sum(ol.quantity)*p.price AS total \n" + 
	        		"    FROM\n" + 
	        		"        mstore_db.order_line ol\n" + 
	        		"    LEFT JOIN mstore_db.product p ON ol.product_number = p.id\n" + 
	        		"    GROUP BY ol.order_number , ol.product_number , p.product_name) op ON o.order_number = op.order_number \n" + 
	        		"WHERE\n" + 
	        		"    YEAR(o.order_date) = :r_year AND MONTH(o.order_date) = 9\n" + 
	        		"	GROUP BY op.product_number , op.product_name, op.total, op.m9\n" + 
	        		"    UNION SELECT \n" + 
	        		"    op.product_number,\n" + 
	        		"    op.product_name,\n" + 
	        		"    0 AS m1,\n" + 
	        		"    0 AS m2,\n" + 
	        		"    0 AS m3,\n" + 
	        		"    0 AS m4,\n" + 
	        		"    0 AS m5,\n" + 
	        		"    0 AS m6,\n" + 
	        		"    0 AS m7,\n" + 
	        		"    0 AS m8,\n" + 
	        		"    0 AS m9,\n" + 
	        		"    sum(op.m10) AS m10,\n" + 
	        		"    0 AS m11,\n" + 
	        		"    0 AS m12,\n" + 
	        		"    sum(op.total) AS total \n" + 
	        		"FROM\n" + 
	        		"    mstore_db.orders o\n" + 
	        		"        LEFT JOIN\n" + 
	        		"    (SELECT \n" + 
	        		"        ol.order_number,\n" + 
	        		"            ol.product_number,\n" + 
	        		"            p.product_name,\n" + 
	        		"            SUM(ol.quantity) AS m10,\n" + 
	        		"            sum(ol.quantity)*p.price AS total \n" + 
	        		"    FROM\n" + 
	        		"        mstore_db.order_line ol\n" + 
	        		"    LEFT JOIN mstore_db.product p ON ol.product_number = p.id\n" + 
	        		"    GROUP BY ol.order_number , ol.product_number , p.product_name) op ON o.order_number = op.order_number \n" + 
	        		"WHERE\n" + 
	        		"    YEAR(o.order_date) = :r_year AND MONTH(o.order_date) = 10\n" + 
	        		"	GROUP BY op.product_number , op.product_name, op.total, op.m10\n" + 
	        		"    UNION SELECT \n" + 
	        		"    op.product_number,\n" + 
	        		"    op.product_name,\n" + 
	        		"    0 AS m1,\n" + 
	        		"    0 AS m2,\n" + 
	        		"    0 AS m3,\n" + 
	        		"    0 AS m4,\n" + 
	        		"    0 AS m5,\n" + 
	        		"    0 AS m6,\n" + 
	        		"    0 AS m7,\n" + 
	        		"    0 AS m8,\n" + 
	        		"    0 AS m9,\n" + 
	        		"    0 AS m10,\n" + 
	        		"    sum(op.m11) AS m11,\n" + 
	        		"    0 AS m12,\n" + 
	        		"    sum(op.total) AS total \n" + 
	        		"FROM\n" + 
	        		"    mstore_db.orders o\n" + 
	        		"        LEFT JOIN\n" + 
	        		"    (SELECT \n" + 
	        		"        ol.order_number,\n" + 
	        		"            ol.product_number,\n" + 
	        		"            p.product_name,\n" + 
	        		"            SUM(ol.quantity) AS m11,\n" + 
	        		"            sum(ol.quantity)*p.price AS total \n" + 
	        		"    FROM\n" + 
	        		"        mstore_db.order_line ol\n" + 
	        		"    LEFT JOIN mstore_db.product p ON ol.product_number = p.id\n" + 
	        		"    GROUP BY ol.order_number , ol.product_number , p.product_name) op ON o.order_number = op.order_number \n" + 
	        		"WHERE\n" + 
	        		"    YEAR(o.order_date) = :r_year AND MONTH(o.order_date) = 11\n" + 
	        		"	GROUP BY op.product_number , op.product_name, op.total, op.m11\n" + 
	        		"    UNION SELECT \n" + 
	        		"    op.product_number,\n" + 
	        		"    op.product_name,\n" + 
	        		"    0 AS m1,\n" + 
	        		"    0 AS m2,\n" + 
	        		"    0 AS m3,\n" + 
	        		"    0 AS m4,\n" + 
	        		"    0 AS m5,\n" + 
	        		"    0 AS m6,\n" + 
	        		"    0 AS m7,\n" + 
	        		"    0 AS m8,\n" + 
	        		"    0 AS m9,\n" + 
	        		"    0 AS m10,\n" + 
	        		"    0 AS m11,\n" + 
	        		"    sum(op.m12) AS m12,\n" + 
	        		"    sum(op.total) AS total \n" + 
	        		"FROM\n" + 
	        		"    mstore_db.orders o\n" + 
	        		"        LEFT JOIN\n" + 
	        		"    (SELECT \n" + 
	        		"        ol.order_number,\n" + 
	        		"            ol.product_number,\n" + 
	        		"            p.product_name,\n" + 
	        		"            SUM(ol.quantity) AS m12,\n" + 
	        		"            sum(ol.quantity)*p.price AS total \n" + 
	        		"    FROM\n" + 
	        		"        mstore_db.order_line ol\n" + 
	        		"    LEFT JOIN mstore_db.product p ON ol.product_number = p.id\n" + 
	        		"    GROUP BY ol.order_number , ol.product_number , p.product_name) op ON o.order_number = op.order_number \n" + 
	        		"WHERE\n" + 
	        		"    YEAR(o.order_date) = :r_year AND MONTH(o.order_date) = 12\n" + 
	        		"	GROUP BY op.product_number , op.product_name, op.total, op.m12) AS w \n" + 
	        		"GROUP BY item_id , item_name",
	        resultSetMapping="MonthlyReportDataMapping"),
	@NamedNativeQuery(
	        name="ReportData.monthlySalesByCategory",
	        query="\n" + 
	        		"# Monthly By Category\n" + 
	        		"\n" + 
	        		"SELECT \n" + 
	        		"	w.category_id AS item_id,\n" + 
	        		"    w.category_name AS item_name,\n" + 
	        		"    sum(w.m1) AS m1_sales,\n" + 
	        		"    sum(w.m2) AS m2_sales,\n" + 
	        		"    sum(w.m3) AS m3_sales,\n" + 
	        		"    sum(w.m4) AS m4_sales,\n" + 
	        		"    sum(w.m5) AS m5_sales,\n" + 
	        		"    sum(w.m6) AS m6_sales,\n" + 
	        		"    sum(w.m7) AS m7_sales,\n" + 
	        		"    sum(w.m7) AS m8_sales,\n" + 
	        		"    sum(w.m7) AS m9_sales,\n" + 
	        		"    sum(w.m7) AS m10_sales,\n" + 
	        		"    sum(w.m7) AS m11_sales,\n" + 
	        		"    sum(w.m7) AS m12_sales,\n" + 
	        		"    sum(w.m1 + w.m2 + w.m3 + w.m4 + w.m5 + w.m6 + w.m7 + w.m8 + w.m9 + w.m10 + w.m11 + w.m12) as total_sales, \n" + 
	        		"    sum(w.total) as total   \n" + 
	        		"FROM (SELECT \n" + 
	        		"    op.category_id,\n" + 
	        		"    op.category_name,\n" + 
	        		"    sum(op.m1) AS m1,\n" + 
	        		"    0 AS m2,\n" + 
	        		"    0 AS m3,\n" + 
	        		"    0 AS m4,\n" + 
	        		"    0 AS m5,\n" + 
	        		"    0 AS m6,\n" + 
	        		"    0 AS m7,\n" + 
	        		"    0 AS m8,\n" + 
	        		"    0 AS m9,\n" + 
	        		"    0 AS m10,\n" + 
	        		"    0 AS m11,\n" + 
	        		"    0 AS m12,\n" + 
	        		"    sum(op.total) AS total \n" + 
	        		"FROM\n" + 
	        		"    mstore_db.orders o\n" + 
	        		"		LEFT JOIN\n" + 
	        		"    (SELECT \n" + 
	        		"        ol.order_number,\n" + 
	        		"            pc.category_id,\n" + 
	        		"			pc.category_name,\n" + 
	        		"            SUM(ol.quantity) AS m1,\n" + 
	        		"            sum(ol.quantity*pc.price) AS total \n" + 
	        		"    FROM\n" + 
	        		"        mstore_db.order_line ol\n" + 
	        		"    LEFT JOIN (SELECT \n" + 
	        		"        p.category_id, c.category_name, p.price, p.id\n" + 
	        		"    FROM\n" + 
	        		"        mstore_db.product p\n" + 
	        		"    LEFT JOIN mstore_db.category c ON c.categoryid = p.category_id) pc ON ol.product_number = pc.id\n" + 
	        		"    GROUP BY ol.order_number , pc.category_id , pc.category_name) op ON o.order_number = op.order_number\n" + 
	        		"WHERE\n" + 
	        		"    YEAR(o.order_date) = :r_year AND MONTH(o.order_date) = 1 \n" + 
	        		"    GROUP BY op.category_id , op.category_name, op.total, op.m1\n" + 
	        		"UNION SELECT \n" + 
	        		"    op.category_id,\n" + 
	        		"    op.category_name,\n" + 
	        		"    0 AS m1,\n" + 
	        		"    sum(op.m2) AS m2,\n" + 
	        		"    0 AS m3,\n" + 
	        		"    0 AS m4,\n" + 
	        		"    0 AS m5,\n" + 
	        		"    0 AS m6,\n" + 
	        		"    0 AS m7,\n" + 
	        		"    0 AS m8,\n" + 
	        		"    0 AS m9,\n" + 
	        		"    0 AS m10,\n" + 
	        		"    0 AS m11,\n" + 
	        		"    0 AS m12,\n" + 
	        		"    sum(op.total) AS total \n" + 
	        		"FROM\n" + 
	        		"    mstore_db.orders o\n" + 
	        		"        LEFT JOIN\n" + 
	        		"    (SELECT \n" + 
	        		"        ol.order_number,\n" + 
	        		"            pc.category_id,\n" + 
	        		"			pc.category_name,\n" + 
	        		"            SUM(ol.quantity) AS m2,\n" + 
	        		"            sum(ol.quantity*pc.price) AS total \n" + 
	        		"    FROM\n" + 
	        		"        mstore_db.order_line ol\n" + 
	        		"    LEFT JOIN (SELECT \n" + 
	        		"        p.category_id, c.category_name, p.price, p.id\n" + 
	        		"    FROM\n" + 
	        		"        mstore_db.product p\n" + 
	        		"    LEFT JOIN mstore_db.category c ON c.categoryid = p.category_id) pc ON ol.product_number = pc.id\n" + 
	        		"    GROUP BY ol.order_number , pc.category_id , pc.category_name) op ON o.order_number = op.order_number\n" + 
	        		"WHERE\n" + 
	        		"    YEAR(o.order_date) = :r_year AND MONTH(o.order_date) = 2\n" + 
	        		"    GROUP BY op.category_id , op.category_name, op.total, op.m2\n" + 
	        		"UNION SELECT \n" + 
	        		"    op.category_id,\n" + 
	        		"    op.category_name,\n" + 
	        		"    0 AS m1,\n" + 
	        		"    0 AS m2,\n" + 
	        		"    sum(op.m3) AS m3,\n" + 
	        		"    0 AS m4,\n" + 
	        		"    0 AS m5,\n" + 
	        		"    0 AS m6,\n" + 
	        		"    0 AS m7,\n" + 
	        		"    0 AS m8,\n" + 
	        		"    0 AS m9,\n" + 
	        		"    0 AS m10,\n" + 
	        		"    0 AS m11,\n" + 
	        		"    0 AS m12,\n" + 
	        		"    sum(op.total) AS total \n" + 
	        		"FROM\n" + 
	        		"    mstore_db.orders o\n" + 
	        		"        LEFT JOIN\n" + 
	        		"    (SELECT \n" + 
	        		"        ol.order_number,\n" + 
	        		"            pc.category_id,\n" + 
	        		"			pc.category_name,\n" + 
	        		"            SUM(ol.quantity) AS m3,\n" + 
	        		"            sum(ol.quantity*pc.price) AS total \n" + 
	        		"    FROM\n" + 
	        		"        mstore_db.order_line ol\n" + 
	        		"    LEFT JOIN (SELECT \n" + 
	        		"        p.category_id, c.category_name, p.price, p.id\n" + 
	        		"    FROM\n" + 
	        		"        mstore_db.product p\n" + 
	        		"    LEFT JOIN mstore_db.category c ON c.categoryid = p.category_id) pc ON ol.product_number = pc.id\n" + 
	        		"    GROUP BY ol.order_number , pc.category_id , pc.category_name) op ON o.order_number = op.order_number\n" + 
	        		"WHERE\n" + 
	        		"    YEAR(o.order_date) = :r_year AND MONTH(o.order_date) = 3\n" + 
	        		"    GROUP BY op.category_id , op.category_name, op.total, op.m3\n" + 
	        		"UNION SELECT \n" + 
	        		"    op.category_id,\n" + 
	        		"    op.category_name,\n" + 
	        		"    0 AS m1,\n" + 
	        		"    0 AS m2,\n" + 
	        		"    0 AS m3,\n" + 
	        		"    sum(op.m4) AS m4,\n" + 
	        		"    0 AS m5,\n" + 
	        		"    0 AS m6,\n" + 
	        		"    0 AS m7,\n" + 
	        		"    0 AS m8,\n" + 
	        		"    0 AS m9,\n" + 
	        		"    0 AS m10,\n" + 
	        		"    0 AS m11,\n" + 
	        		"    0 AS m12,\n" + 
	        		"    sum(op.total) AS total \n" + 
	        		"FROM\n" + 
	        		"    mstore_db.orders o\n" + 
	        		"        LEFT JOIN\n" + 
	        		"    (SELECT \n" + 
	        		"        ol.order_number,\n" + 
	        		"            pc.category_id,\n" + 
	        		"			pc.category_name,\n" + 
	        		"            SUM(ol.quantity) AS m4,\n" + 
	        		"            sum(ol.quantity*pc.price) AS total \n" + 
	        		"    FROM\n" + 
	        		"        mstore_db.order_line ol\n" + 
	        		"    LEFT JOIN (SELECT \n" + 
	        		"        p.category_id, c.category_name, p.price, p.id\n" + 
	        		"    FROM\n" + 
	        		"        mstore_db.product p\n" + 
	        		"    LEFT JOIN mstore_db.category c ON c.categoryid = p.category_id) pc ON ol.product_number = pc.id\n" + 
	        		"    GROUP BY ol.order_number , pc.category_id , pc.category_name) op ON o.order_number = op.order_number\n" + 
	        		"WHERE\n" + 
	        		"    YEAR(o.order_date) = :r_year AND MONTH(o.order_date) = 4\n" + 
	        		"    GROUP BY op.category_id , op.category_name, op.total, op.m4\n" + 
	        		"UNION SELECT \n" + 
	        		"    op.category_id,\n" + 
	        		"    op.category_name,\n" + 
	        		"    0 AS m1,\n" + 
	        		"    0 AS m2,\n" + 
	        		"    0 AS m3,\n" + 
	        		"    0 AS m4,\n" + 
	        		"    sum(op.m5) AS m5,\n" + 
	        		"    0 AS m6,\n" + 
	        		"    0 AS m7,\n" + 
	        		"    0 AS m8,\n" + 
	        		"    0 AS m9,\n" + 
	        		"    0 AS m10,\n" + 
	        		"    0 AS m11,\n" + 
	        		"    0 AS m12,\n" + 
	        		"    sum(op.total) AS total \n" + 
	        		"FROM\n" + 
	        		"    mstore_db.orders o\n" + 
	        		"        LEFT JOIN\n" + 
	        		"    (SELECT \n" + 
	        		"        ol.order_number,\n" + 
	        		"            pc.category_id,\n" + 
	        		"			pc.category_name,\n" + 
	        		"            SUM(ol.quantity) AS m5,\n" + 
	        		"            sum(ol.quantity*pc.price) AS total \n" + 
	        		"    FROM\n" + 
	        		"        mstore_db.order_line ol\n" + 
	        		"    LEFT JOIN (SELECT \n" + 
	        		"        p.category_id, c.category_name, p.price, p.id\n" + 
	        		"    FROM\n" + 
	        		"        mstore_db.product p\n" + 
	        		"    LEFT JOIN mstore_db.category c ON c.categoryid = p.category_id) pc ON ol.product_number = pc.id\n" + 
	        		"    GROUP BY ol.order_number , pc.category_id , pc.category_name) op ON o.order_number = op.order_number\n" + 
	        		"WHERE\n" + 
	        		"    YEAR(o.order_date) = :r_year AND MONTH(o.order_date) = 5\n" + 
	        		"    GROUP BY op.category_id , op.category_name, op.total, op.m5\n" + 
	        		"UNION SELECT \n" + 
	        		"    op.category_id,\n" + 
	        		"    op.category_name,\n" + 
	        		"    0 AS m1,\n" + 
	        		"    0 AS m2,\n" + 
	        		"    0 AS m3,\n" + 
	        		"    0 AS m4,\n" + 
	        		"    0 AS m5,\n" + 
	        		"    sum(op.m6) AS m6,\n" + 
	        		"    0 AS m7,\n" + 
	        		"    0 AS m8,\n" + 
	        		"    0 AS m9,\n" + 
	        		"    0 AS m10,\n" + 
	        		"    0 AS m11,\n" + 
	        		"    0 AS m12,\n" + 
	        		"    sum(op.total) AS total \n" + 
	        		"FROM\n" + 
	        		"    mstore_db.orders o\n" + 
	        		"        LEFT JOIN\n" + 
	        		"    (SELECT \n" + 
	        		"        ol.order_number,\n" + 
	        		"            pc.category_id,\n" + 
	        		"			pc.category_name,\n" + 
	        		"            SUM(ol.quantity) AS m6,\n" + 
	        		"            sum(ol.quantity*pc.price) AS total \n" + 
	        		"    FROM\n" + 
	        		"        mstore_db.order_line ol\n" + 
	        		"    LEFT JOIN (SELECT \n" + 
	        		"        p.category_id, c.category_name, p.price, p.id\n" + 
	        		"    FROM\n" + 
	        		"        mstore_db.product p\n" + 
	        		"    LEFT JOIN mstore_db.category c ON c.categoryid = p.category_id) pc ON ol.product_number = pc.id\n" + 
	        		"    GROUP BY ol.order_number , pc.category_id , pc.category_name) op ON o.order_number = op.order_number\n" + 
	        		"WHERE\n" + 
	        		"    YEAR(o.order_date) = :r_year AND MONTH(o.order_date) = 6  \n" + 
	        		"    GROUP BY op.category_id , op.category_name, op.total, op.m6\n" + 
	        		"UNION SELECT \n" + 
	        		"    op.category_id,\n" + 
	        		"    op.category_name,\n" + 
	        		"    0 AS m1,\n" + 
	        		"    0 AS m2,\n" + 
	        		"    0 AS m3,\n" + 
	        		"    0 AS m4,\n" + 
	        		"    0 AS m5,\n" + 
	        		"    0 AS m6,\n" + 
	        		"    sum(op.m7) AS m7,\n" + 
	        		"    0 AS m8,\n" + 
	        		"    0 AS m9,\n" + 
	        		"    0 AS m10,\n" + 
	        		"    0 AS m11,\n" + 
	        		"    0 AS m12,\n" + 
	        		"    sum(op.total) AS total \n" + 
	        		"FROM\n" + 
	        		"    mstore_db.orders o\n" + 
	        		"        LEFT JOIN\n" + 
	        		"    (SELECT \n" + 
	        		"        ol.order_number,\n" + 
	        		"            pc.category_id,\n" + 
	        		"			pc.category_name,\n" + 
	        		"            SUM(ol.quantity) AS m7,\n" + 
	        		"            sum(ol.quantity*pc.price) AS total \n" + 
	        		"    FROM\n" + 
	        		"        mstore_db.order_line ol\n" + 
	        		"    LEFT JOIN (SELECT \n" + 
	        		"        p.category_id, c.category_name, p.price, p.id\n" + 
	        		"    FROM\n" + 
	        		"        mstore_db.product p\n" + 
	        		"    LEFT JOIN mstore_db.category c ON c.categoryid = p.category_id) pc ON ol.product_number = pc.id\n" + 
	        		"    GROUP BY ol.order_number , pc.category_id , pc.category_name) op ON o.order_number = op.order_number\n" + 
	        		"WHERE\n" + 
	        		"    YEAR(o.order_date) = :r_year AND MONTH(o.order_date) = 7\n" + 
	        		"	GROUP BY op.category_id , op.category_name, op.total, op.m7\n" + 
	        		"UNION SELECT \n" + 
	        		"    op.category_id,\n" + 
	        		"    op.category_name,\n" + 
	        		"    0 AS m1,\n" + 
	        		"    0 AS m2,\n" + 
	        		"    0 AS m3,\n" + 
	        		"    0 AS m4,\n" + 
	        		"    0 AS m5,\n" + 
	        		"    0 AS m6,\n" + 
	        		"    0 AS m7,\n" + 
	        		"    sum(op.m8) AS m8,\n" + 
	        		"    0 AS m9,\n" + 
	        		"    0 AS m10,\n" + 
	        		"    0 AS m11,\n" + 
	        		"    0 AS m12,\n" + 
	        		"    sum(op.total) AS total \n" + 
	        		"FROM\n" + 
	        		"    mstore_db.orders o\n" + 
	        		"        LEFT JOIN\n" + 
	        		"    (SELECT \n" + 
	        		"        ol.order_number,\n" + 
	        		"            pc.category_id,\n" + 
	        		"			pc.category_name,\n" + 
	        		"            SUM(ol.quantity) AS m8,\n" + 
	        		"            sum(ol.quantity*pc.price) AS total \n" + 
	        		"    FROM\n" + 
	        		"        mstore_db.order_line ol\n" + 
	        		"    LEFT JOIN (SELECT \n" + 
	        		"        p.category_id, c.category_name, p.price, p.id\n" + 
	        		"    FROM\n" + 
	        		"        mstore_db.product p\n" + 
	        		"    LEFT JOIN mstore_db.category c ON c.categoryid = p.category_id) pc ON ol.product_number = pc.id\n" + 
	        		"    GROUP BY ol.order_number , pc.category_id , pc.category_name) op ON o.order_number = op.order_number\n" + 
	        		"WHERE\n" + 
	        		"    YEAR(o.order_date) = :r_year AND MONTH(o.order_date) = 8\n" + 
	        		"	GROUP BY op.category_id , op.category_name, op.total, op.m8\n" + 
	        		"    UNION SELECT \n" + 
	        		"    op.category_id,\n" + 
	        		"    op.category_name,\n" + 
	        		"    0 AS m1,\n" + 
	        		"    0 AS m2,\n" + 
	        		"    0 AS m3,\n" + 
	        		"    0 AS m4,\n" + 
	        		"    0 AS m5,\n" + 
	        		"    0 AS m6,\n" + 
	        		"    0 AS m7,\n" + 
	        		"    0 AS m8,\n" + 
	        		"    sum(op.m9) AS m9,\n" + 
	        		"    0 AS m10,\n" + 
	        		"    0 AS m11,\n" + 
	        		"    0 AS m12,\n" + 
	        		"    sum(op.total) AS total \n" + 
	        		"FROM\n" + 
	        		"    mstore_db.orders o\n" + 
	        		"        LEFT JOIN\n" + 
	        		"    (SELECT \n" + 
	        		"        ol.order_number,\n" + 
	        		"            pc.category_id,\n" + 
	        		"			pc.category_name,\n" + 
	        		"            SUM(ol.quantity) AS m9,\n" + 
	        		"            sum(ol.quantity*pc.price) AS total \n" + 
	        		"    FROM\n" + 
	        		"        mstore_db.order_line ol\n" + 
	        		"    LEFT JOIN (SELECT \n" + 
	        		"        p.category_id, c.category_name, p.price, p.id\n" + 
	        		"    FROM\n" + 
	        		"        mstore_db.product p\n" + 
	        		"    LEFT JOIN mstore_db.category c ON c.categoryid = p.category_id) pc ON ol.product_number = pc.id\n" + 
	        		"    GROUP BY ol.order_number , pc.category_id , pc.category_name) op ON o.order_number = op.order_number\n" + 
	        		"WHERE\n" + 
	        		"    YEAR(o.order_date) = :r_year AND MONTH(o.order_date) = 9\n" + 
	        		"	GROUP BY op.category_id , op.category_name, op.total, op.m9\n" + 
	        		"    UNION SELECT \n" + 
	        		"    op.category_id,\n" + 
	        		"    op.category_name,\n" + 
	        		"    0 AS m1,\n" + 
	        		"    0 AS m2,\n" + 
	        		"    0 AS m3,\n" + 
	        		"    0 AS m4,\n" + 
	        		"    0 AS m5,\n" + 
	        		"    0 AS m6,\n" + 
	        		"    0 AS m7,\n" + 
	        		"    0 AS m8,\n" + 
	        		"    0 AS m9,\n" + 
	        		"    sum(op.m10) AS m10,\n" + 
	        		"    0 AS m11,\n" + 
	        		"    0 AS m12,\n" + 
	        		"    sum(op.total) AS total \n" + 
	        		"FROM\n" + 
	        		"    mstore_db.orders o\n" + 
	        		"        LEFT JOIN\n" + 
	        		"    (SELECT \n" + 
	        		"        ol.order_number,\n" + 
	        		"            pc.category_id,\n" + 
	        		"			pc.category_name,\n" + 
	        		"            SUM(ol.quantity) AS m10,\n" + 
	        		"            sum(ol.quantity*pc.price) AS total \n" + 
	        		"    FROM\n" + 
	        		"        mstore_db.order_line ol\n" + 
	        		"    LEFT JOIN (SELECT \n" + 
	        		"        p.category_id, c.category_name, p.price, p.id\n" + 
	        		"    FROM\n" + 
	        		"        mstore_db.product p\n" + 
	        		"    LEFT JOIN mstore_db.category c ON c.categoryid = p.category_id) pc ON ol.product_number = pc.id\n" + 
	        		"    GROUP BY ol.order_number , pc.category_id , pc.category_name) op ON o.order_number = op.order_number\n" + 
	        		"WHERE\n" + 
	        		"    YEAR(o.order_date) = :r_year AND MONTH(o.order_date) = 10\n" + 
	        		"	GROUP BY op.category_id , op.category_name, op.total, op.m10\n" + 
	        		"UNION SELECT \n" + 
	        		"    op.category_id,\n" + 
	        		"    op.category_name,\n" + 
	        		"    0 AS m1,\n" + 
	        		"    0 AS m2,\n" + 
	        		"    0 AS m3,\n" + 
	        		"    0 AS m4,\n" + 
	        		"    0 AS m5,\n" + 
	        		"    0 AS m6,\n" + 
	        		"    0 AS m7,\n" + 
	        		"    0 AS m8,\n" + 
	        		"    0 AS m9,\n" + 
	        		"    0 AS m10,\n" + 
	        		"    sum(op.m11) AS m11,\n" + 
	        		"    0 AS m12,\n" + 
	        		"    sum(op.total) AS total \n" + 
	        		"FROM\n" + 
	        		"    mstore_db.orders o\n" + 
	        		"        LEFT JOIN\n" + 
	        		"    (SELECT \n" + 
	        		"        ol.order_number,\n" + 
	        		"            pc.category_id,\n" + 
	        		"			pc.category_name,\n" + 
	        		"            SUM(ol.quantity) AS m11,\n" + 
	        		"            sum(ol.quantity*pc.price) AS total \n" + 
	        		"    FROM\n" + 
	        		"        mstore_db.order_line ol\n" + 
	        		"    LEFT JOIN (SELECT \n" + 
	        		"        p.category_id, c.category_name, p.price, p.id\n" + 
	        		"    FROM\n" + 
	        		"        mstore_db.product p\n" + 
	        		"    LEFT JOIN mstore_db.category c ON c.categoryid = p.category_id) pc ON ol.product_number = pc.id\n" + 
	        		"    GROUP BY ol.order_number , pc.category_id , pc.category_name) op ON o.order_number = op.order_number\n" + 
	        		"WHERE\n" + 
	        		"    YEAR(o.order_date) = :r_year AND MONTH(o.order_date) = 11 \n" + 
	        		"	GROUP BY op.category_id , op.category_name, op.total, op.m11\n" + 
	        		"    UNION SELECT \n" + 
	        		"    op.category_id,\n" + 
	        		"    op.category_name,\n" + 
	        		"    0 AS m1,\n" + 
	        		"    0 AS m2,\n" + 
	        		"    0 AS m3,\n" + 
	        		"    0 AS m4,\n" + 
	        		"    0 AS m5,\n" + 
	        		"    0 AS m6,\n" + 
	        		"    0 AS m7,\n" + 
	        		"    0 AS m8,\n" + 
	        		"    0 AS m9,\n" + 
	        		"    0 AS m10,\n" + 
	        		"    0 AS m11,\n" + 
	        		"    sum(op.m12) AS m12,\n" + 
	        		"    sum(op.total) AS total \n" + 
	        		"FROM\n" + 
	        		"    mstore_db.orders o\n" + 
	        		"        LEFT JOIN\n" + 
	        		"    (SELECT \n" + 
	        		"        ol.order_number,\n" + 
	        		"            pc.category_id,\n" + 
	        		"			pc.category_name,\n" + 
	        		"            SUM(ol.quantity) AS m12,\n" + 
	        		"            sum(ol.quantity*pc.price) AS total \n" + 
	        		"    FROM\n" + 
	        		"        mstore_db.order_line ol\n" + 
	        		"    LEFT JOIN (SELECT \n" + 
	        		"        p.category_id, c.category_name, p.price, p.id\n" + 
	        		"    FROM\n" + 
	        		"        mstore_db.product p\n" + 
	        		"    LEFT JOIN mstore_db.category c ON c.categoryid = p.category_id) pc ON ol.product_number = pc.id\n" + 
	        		"    GROUP BY ol.order_number , pc.category_id , pc.category_name) op ON o.order_number = op.order_number\n" + 
	        		"WHERE\n" + 
	        		"    YEAR(o.order_date) = :r_year AND MONTH(o.order_date) = 12\n" + 
	        		"	GROUP BY op.category_id , op.category_name, op.total, op.m12) AS w \n" + 
	        		"GROUP BY item_id , item_name",
	        resultSetMapping="MonthlyReportDataMapping"),
	@NamedNativeQuery(
	        name="ReportData.monthlyOverallSales",
	        query="SELECT \n" + 
	        		"	sum(w.m1) AS m1_sales,\n" + 
	        		"    sum(w.m2) AS m2_sales,\n" + 
	        		"    sum(w.m3) AS m3_sales,\n" + 
	        		"    sum(w.m4) AS m4_sales,\n" + 
	        		"    sum(w.m5) AS m5_sales,\n" + 
	        		"    sum(w.m6) AS m6_sales,\n" + 
	        		"    sum(w.m7) AS m7_sales,\n" + 
	        		"    sum(w.m7) AS m8_sales,\n" + 
	        		"    sum(w.m7) AS m9_sales,\n" + 
	        		"    sum(w.m7) AS m10_sales,\n" + 
	        		"    sum(w.m7) AS m11_sales,\n" + 
	        		"    sum(w.m7) AS m12_sales,\n" + 
	        		"    sum(w.m1 + w.m2 + w.m3 + w.m4 + w.m5 + w.m6 + w.m7 + w.m8 + w.m9 + w.m10 + w.m11 + w.m12) as total_sales, \n" + 
	        		"    sum(w.total) as total \n" + 
	        		"FROM (SELECT \n" + 
	        		"    sum(op.m1) AS m1,\n" + 
	        		"    0 AS m2,\n" + 
	        		"    0 AS m3,\n" + 
	        		"    0 AS m4,\n" + 
	        		"    0 AS m5,\n" + 
	        		"    0 AS m6,\n" + 
	        		"    0 AS m7,\n" + 
	        		"    0 AS m8,\n" + 
	        		"    0 AS m9,\n" + 
	        		"    0 AS m10,\n" + 
	        		"    0 AS m11,\n" + 
	        		"    0 AS m12,\n" + 
	        		"    sum(op.total) AS total \n" + 
	        		"FROM\n" + 
	        		"    mstore_db.orders o\n" + 
	        		"        LEFT JOIN\n" + 
	        		"    (SELECT \n" + 
	        		"        ol.order_number,\n" + 
	        		"            SUM(ol.quantity) AS m1,\n" + 
	        		"            sum(ol.quantity)*p.price AS total \n" + 
	        		"    FROM\n" + 
	        		"        mstore_db.order_line ol\n" + 
	        		"    LEFT JOIN mstore_db.product p ON ol.product_number = p.id\n" + 
	        		"    GROUP BY ol.order_number, p.price) op ON o.order_number = op.order_number \n" + 
	        		"WHERE\n" + 
	        		"	YEAR(o.order_date) = :r_year AND MONTH(o.order_date) = 1 \n" + 
	        		"UNION SELECT \n" + 
	        		"    0 AS m1,\n" + 
	        		"    sum(op.m2) AS m2,\n" + 
	        		"    0 AS m3,\n" + 
	        		"    0 AS m4,\n" + 
	        		"    0 AS m5,\n" + 
	        		"    0 AS m6,\n" + 
	        		"    0 AS m7,\n" + 
	        		"    0 AS m8,\n" + 
	        		"    0 AS m9,\n" + 
	        		"    0 AS m10,\n" + 
	        		"    0 AS m11,\n" + 
	        		"    0 AS m12,\n" + 
	        		"    sum(op.total) AS total \n" + 
	        		"FROM\n" + 
	        		"    mstore_db.orders o\n" + 
	        		"        LEFT JOIN\n" + 
	        		"    (SELECT \n" + 
	        		"        ol.order_number,\n" + 
	        		"            SUM(ol.quantity) AS m2,\n" + 
	        		"            sum(ol.quantity)*p.price AS total \n" + 
	        		"    FROM\n" + 
	        		"        mstore_db.order_line ol\n" + 
	        		"    LEFT JOIN mstore_db.product p ON ol.product_number = p.id\n" + 
	        		"    GROUP BY ol.order_number, p.price) op ON o.order_number = op.order_number \n" + 
	        		"WHERE\n" + 
	        		"    YEAR(o.order_date) = :r_year AND MONTH(o.order_date) = 2 \n" + 
	        		"	GROUP BY m2, total \n" + 
	        		"UNION SELECT \n" + 
	        		"    0 AS m1,\n" + 
	        		"    0 AS m2,\n" + 
	        		"    sum(op.m3) AS m3,\n" + 
	        		"    0 AS m4,\n" + 
	        		"    0 AS m5,\n" + 
	        		"    0 AS m6,\n" + 
	        		"    0 AS m7,\n" + 
	        		"    0 AS m8,\n" + 
	        		"    0 AS m9,\n" + 
	        		"    0 AS m10,\n" + 
	        		"    0 AS m11,\n" + 
	        		"    0 AS m12,\n" + 
	        		"    sum(op.total) AS total \n" + 
	        		"FROM\n" + 
	        		"    mstore_db.orders o\n" + 
	        		"        LEFT JOIN\n" + 
	        		"    (SELECT \n" + 
	        		"        ol.order_number,\n" + 
	        		"            SUM(ol.quantity) AS m3,\n" + 
	        		"            sum(ol.quantity)*p.price AS total \n" + 
	        		"    FROM\n" + 
	        		"        mstore_db.order_line ol\n" + 
	        		"    LEFT JOIN mstore_db.product p ON ol.product_number = p.id\n" + 
	        		"    GROUP BY ol.order_number, p.price) op ON o.order_number = op.order_number \n" + 
	        		"WHERE\n" + 
	        		"    YEAR(o.order_date) = :r_year AND MONTH(o.order_date) = 3\n" + 
	        		"    GROUP BY m3, total \n" + 
	        		"UNION SELECT \n" + 
	        		"    0 AS m1,\n" + 
	        		"    0 AS m2,\n" + 
	        		"    0 AS m3,\n" + 
	        		"    sum(op.m4) AS m4,\n" + 
	        		"    0 AS m5,\n" + 
	        		"    0 AS m6,\n" + 
	        		"    0 AS m7,\n" + 
	        		"    0 AS m8,\n" + 
	        		"    0 AS m9,\n" + 
	        		"    0 AS m10,\n" + 
	        		"    0 AS m11,\n" + 
	        		"    0 AS m12,\n" + 
	        		"    sum(op.total) AS total \n" + 
	        		"FROM\n" + 
	        		"    mstore_db.orders o\n" + 
	        		"        LEFT JOIN\n" + 
	        		"    (SELECT \n" + 
	        		"        ol.order_number,\n" + 
	        		"            SUM(ol.quantity) AS m4,\n" + 
	        		"            sum(ol.quantity)*p.price AS total \n" + 
	        		"    FROM\n" + 
	        		"        mstore_db.order_line ol\n" + 
	        		"    LEFT JOIN mstore_db.product p ON ol.product_number = p.id\n" + 
	        		"    GROUP BY ol.order_number, p.price) op ON o.order_number = op.order_number \n" + 
	        		"WHERE\n" + 
	        		"    YEAR(o.order_date) = :r_year AND MONTH(o.order_date) = 4\n" + 
	        		"    GROUP BY m4, total \n" + 
	        		"UNION SELECT \n" + 
	        		"    0 AS m1,\n" + 
	        		"    0 AS m2,\n" + 
	        		"    0 AS m3,\n" + 
	        		"    0 AS m4,\n" + 
	        		"    sum(op.m5) AS m5,\n" + 
	        		"    0 AS m6,\n" + 
	        		"    0 AS m7,\n" + 
	        		"    0 AS m8,\n" + 
	        		"    0 AS m9,\n" + 
	        		"    0 AS m10,\n" + 
	        		"    0 AS m11,\n" + 
	        		"    0 AS m12,\n" + 
	        		"    sum(op.total) AS total \n" + 
	        		"FROM\n" + 
	        		"    mstore_db.orders o\n" + 
	        		"        LEFT JOIN\n" + 
	        		"    (SELECT \n" + 
	        		"        ol.order_number,\n" + 
	        		"            SUM(ol.quantity) AS m5,\n" + 
	        		"            sum(ol.quantity)*p.price AS total \n" + 
	        		"    FROM\n" + 
	        		"        mstore_db.order_line ol\n" + 
	        		"    LEFT JOIN mstore_db.product p ON ol.product_number = p.id\n" + 
	        		"    GROUP BY ol.order_number, p.price) op ON o.order_number = op.order_number \n" + 
	        		"WHERE\n" + 
	        		"    YEAR(o.order_date) = :r_year AND MONTH(o.order_date) = 5 \n" + 
	        		"    GROUP BY m5, total \n" + 
	        		"UNION SELECT \n" + 
	        		"    0 AS m1,\n" + 
	        		"    0 AS m2,\n" + 
	        		"    0 AS m3,\n" + 
	        		"    0 AS m4,\n" + 
	        		"    0 AS m5,\n" + 
	        		"    sum(op.m6) AS m6,\n" + 
	        		"    0 AS m7,\n" + 
	        		"    0 AS m8,\n" + 
	        		"    0 AS m9,\n" + 
	        		"    0 AS m10,\n" + 
	        		"    0 AS m11,\n" + 
	        		"    0 AS m12,\n" + 
	        		"    sum(op.total) AS total \n" + 
	        		"FROM\n" + 
	        		"    mstore_db.orders o\n" + 
	        		"        LEFT JOIN\n" + 
	        		"    (SELECT \n" + 
	        		"        ol.order_number,\n" + 
	        		"            SUM(ol.quantity) AS m6,\n" + 
	        		"            sum(ol.quantity)*p.price AS total \n" + 
	        		"    FROM\n" + 
	        		"        mstore_db.order_line ol\n" + 
	        		"    LEFT JOIN mstore_db.product p ON ol.product_number = p.id\n" + 
	        		"    GROUP BY ol.order_number, p.price) op ON o.order_number = op.order_number \n" + 
	        		"WHERE\n" + 
	        		"    YEAR(o.order_date) = :r_year AND MONTH(o.order_date) = 6  \n" + 
	        		"    GROUP BY m6, total \n" + 
	        		"UNION SELECT \n" + 
	        		"    0 AS m1,\n" + 
	        		"    0 AS m2,\n" + 
	        		"    0 AS m3,\n" + 
	        		"    0 AS m4,\n" + 
	        		"    0 AS m5,\n" + 
	        		"    0 AS m6,\n" + 
	        		"    sum(op.m7) AS m7,\n" + 
	        		"    0 AS m8,\n" + 
	        		"    0 AS m9,\n" + 
	        		"    0 AS m10,\n" + 
	        		"    0 AS m11,\n" + 
	        		"    0 AS m12,\n" + 
	        		"    sum(op.total) AS total \n" + 
	        		"FROM\n" + 
	        		"    mstore_db.orders o\n" + 
	        		"        LEFT JOIN\n" + 
	        		"    (SELECT \n" + 
	        		"        ol.order_number,\n" + 
	        		"            SUM(ol.quantity) AS m7,\n" + 
	        		"            sum(ol.quantity)*p.price AS total \n" + 
	        		"    FROM\n" + 
	        		"        mstore_db.order_line ol\n" + 
	        		"    LEFT JOIN mstore_db.product p ON ol.product_number = p.id\n" + 
	        		"    GROUP BY ol.order_number, p.price) op ON o.order_number = op.order_number \n" + 
	        		"WHERE\n" + 
	        		"    YEAR(o.order_date) = :r_year AND MONTH(o.order_date) = 7 \n" + 
	        		"    GROUP BY m7, total \n" + 
	        		"UNION SELECT \n" + 
	        		"    0 AS m1,\n" + 
	        		"    0 AS m2,\n" + 
	        		"    0 AS m3,\n" + 
	        		"    0 AS m4,\n" + 
	        		"    0 AS m5,\n" + 
	        		"    0 AS m6,\n" + 
	        		"    0 AS m7,\n" + 
	        		"    sum(op.m8) AS m8,\n" + 
	        		"    0 AS m9,\n" + 
	        		"    0 AS m10,\n" + 
	        		"    0 AS m11,\n" + 
	        		"    0 AS m12,\n" + 
	        		"    sum(op.total) AS total \n" + 
	        		"FROM\n" + 
	        		"    mstore_db.orders o\n" + 
	        		"        LEFT JOIN\n" + 
	        		"    (SELECT \n" + 
	        		"        ol.order_number,\n" + 
	        		"            SUM(ol.quantity) AS m8,\n" + 
	        		"            sum(ol.quantity)*p.price AS total \n" + 
	        		"    FROM\n" + 
	        		"        mstore_db.order_line ol\n" + 
	        		"    LEFT JOIN mstore_db.product p ON ol.product_number = p.id\n" + 
	        		"    GROUP BY ol.order_number, p.price) op ON o.order_number = op.order_number \n" + 
	        		"WHERE\n" + 
	        		"    YEAR(o.order_date) = :r_year AND MONTH(o.order_date) = 8 \n" + 
	        		"    GROUP BY m8, total \n" + 
	        		"UNION SELECT \n" + 
	        		"    0 AS m1,\n" + 
	        		"    0 AS m2,\n" + 
	        		"    0 AS m3,\n" + 
	        		"    0 AS m4,\n" + 
	        		"    0 AS m5,\n" + 
	        		"    0 AS m6,\n" + 
	        		"    0 AS m7,\n" + 
	        		"    0 AS m8,\n" + 
	        		"    sum(op.m9) AS m9,\n" + 
	        		"    0 AS m10,\n" + 
	        		"    0 AS m11,\n" + 
	        		"    0 AS m12,\n" + 
	        		"    sum(op.total) AS total \n" + 
	        		"FROM\n" + 
	        		"    mstore_db.orders o\n" + 
	        		"        LEFT JOIN\n" + 
	        		"    (SELECT \n" + 
	        		"        ol.order_number,\n" + 
	        		"            SUM(ol.quantity) AS m9,\n" + 
	        		"            sum(ol.quantity)*p.price AS total \n" + 
	        		"    FROM\n" + 
	        		"        mstore_db.order_line ol\n" + 
	        		"    LEFT JOIN mstore_db.product p ON ol.product_number = p.id\n" + 
	        		"    GROUP BY ol.order_number, p.price) op ON o.order_number = op.order_number \n" + 
	        		"WHERE\n" + 
	        		"    YEAR(o.order_date) = :r_year AND MONTH(o.order_date) = 9 \n" + 
	        		"    GROUP BY m9, total \n" + 
	        		"UNION SELECT \n" + 
	        		"    0 AS m1,\n" + 
	        		"    0 AS m2,\n" + 
	        		"    0 AS m3,\n" + 
	        		"    0 AS m4,\n" + 
	        		"    0 AS m5,\n" + 
	        		"    0 AS m6,\n" + 
	        		"    0 AS m7,\n" + 
	        		"    0 AS m8,\n" + 
	        		"    0 AS m9,\n" + 
	        		"    sum(op.m10) AS m10,\n" + 
	        		"    0 AS m11,\n" + 
	        		"    0 AS m12,\n" + 
	        		"    sum(op.total) AS total \n" + 
	        		"FROM\n" + 
	        		"    mstore_db.orders o\n" + 
	        		"        LEFT JOIN\n" + 
	        		"    (SELECT \n" + 
	        		"        ol.order_number,\n" + 
	        		"            SUM(ol.quantity) AS m10,\n" + 
	        		"            sum(ol.quantity)*p.price AS total \n" + 
	        		"    FROM\n" + 
	        		"        mstore_db.order_line ol\n" + 
	        		"    LEFT JOIN mstore_db.product p ON ol.product_number = p.id\n" + 
	        		"    GROUP BY ol.order_number, p.price) op ON o.order_number = op.order_number \n" + 
	        		"WHERE\n" + 
	        		"    YEAR(o.order_date) = :r_year AND MONTH(o.order_date) = 10 \n" + 
	        		"    GROUP BY m10, total \n" + 
	        		"UNION SELECT \n" + 
	        		"    0 AS m1,\n" + 
	        		"    0 AS m2,\n" + 
	        		"    0 AS m3,\n" + 
	        		"    0 AS m4,\n" + 
	        		"    0 AS m5,\n" + 
	        		"    0 AS m6,\n" + 
	        		"    0 AS m7,\n" + 
	        		"    0 AS m8,\n" + 
	        		"    0 AS m9,\n" + 
	        		"    0 AS m10,\n" + 
	        		"    sum(op.m11) AS m11,\n" + 
	        		"    0 AS m12,\n" + 
	        		"    sum(op.total) AS total \n" + 
	        		"FROM\n" + 
	        		"    mstore_db.orders o\n" + 
	        		"        LEFT JOIN\n" + 
	        		"    (SELECT \n" + 
	        		"        ol.order_number,\n" + 
	        		"            SUM(ol.quantity) AS m11,\n" + 
	        		"            sum(ol.quantity)*p.price AS total \n" + 
	        		"    FROM\n" + 
	        		"        mstore_db.order_line ol\n" + 
	        		"    LEFT JOIN mstore_db.product p ON ol.product_number = p.id\n" + 
	        		"    GROUP BY ol.order_number, p.price) op ON o.order_number = op.order_number \n" + 
	        		"WHERE\n" + 
	        		"    YEAR(o.order_date) = :r_year AND MONTH(o.order_date) = 11 \n" + 
	        		"    GROUP BY m11, total \n" + 
	        		"UNION SELECT \n" + 
	        		"    0 AS m1,\n" + 
	        		"    0 AS m2,\n" + 
	        		"    0 AS m3,\n" + 
	        		"    0 AS m4,\n" + 
	        		"    0 AS m5,\n" + 
	        		"    0 AS m6,\n" + 
	        		"    0 AS m7,\n" + 
	        		"    0 AS m8,\n" + 
	        		"    0 AS m9,\n" + 
	        		"    0 AS m10,\n" + 
	        		"    0 AS m11,\n" + 
	        		"    sum(op.m12) AS m12,\n" + 
	        		"    sum(op.total) AS total \n" + 
	        		"FROM\n" + 
	        		"    mstore_db.orders o\n" + 
	        		"        LEFT JOIN\n" + 
	        		"    (SELECT \n" + 
	        		"        ol.order_number,\n" + 
	        		"            SUM(ol.quantity) AS m12,\n" + 
	        		"            sum(ol.quantity)*p.price AS total \n" + 
	        		"    FROM\n" + 
	        		"        mstore_db.order_line ol\n" + 
	        		"    LEFT JOIN mstore_db.product p ON ol.product_number = p.id\n" + 
	        		"    GROUP BY ol.order_number, p.price) op ON o.order_number = op.order_number \n" + 
	        		"WHERE\n" + 
	        		"    YEAR(o.order_date) = :r_year AND MONTH(o.order_date) = 12) AS w",
	        resultSetMapping="MonthlyOverallReportDataMapping"),
	@NamedNativeQuery(
	        name="ReportData.quarterlySalesByProduct",
	        query="SELECT \n" + 
	        		"	w.product_number AS item_id,\n" + 
	        		"    w.product_name AS item_name,\n" + 
	        		"    sum(w.q1) AS q1_sales,\n" + 
	        		"    sum(w.q2) AS q2_sales,\n" + 
	        		"    sum(w.q3) AS q3_sales,\n" + 
	        		"    sum(w.q4) AS q4_sales,\n" + 
	        		"    sum(w.q1 + w.q2 + w.q3 + w.q4) as total_sales, \n" + 
	        		"    sum(w.total) as total   \n" + 
	        		"FROM (SELECT \n" + 
	        		"    op.product_number,\n" + 
	        		"    op.product_name,\n" + 
	        		"    sum(op.q1) AS q1,\n" + 
	        		"    0 AS q2,\n" + 
	        		"    0 AS q3,\n" + 
	        		"    0 AS q4,\n" + 
	        		"    sum(op.total) AS total \n" + 
	        		"FROM\n" + 
	        		"    mstore_db.orders o\n" + 
	        		"        LEFT JOIN\n" + 
	        		"    (SELECT \n" + 
	        		"        ol.order_number,\n" + 
	        		"            ol.product_number,\n" + 
	        		"            p.product_name,\n" + 
	        		"            SUM(ol.quantity) AS q1,\n" + 
	        		"            sum(ol.quantity*p.price) AS total \n" + 
	        		"    FROM\n" + 
	        		"        mstore_db.order_line ol\n" + 
	        		"    LEFT JOIN mstore_db.product p ON ol.product_number = p.id\n" + 
	        		"    GROUP BY ol.order_number , ol.product_number , p.product_name) op ON o.order_number = op.order_number\n" + 
	        		"WHERE\n" + 
	        		"    YEAR(o.order_date) = 2019 AND 1 <= MONTH(o.order_date) AND MONTH(o.order_date) <= 3  \n" + 
	        		"    GROUP BY op.product_number , op.product_name, op.total, op.q1\n" + 
	        		"UNION SELECT \n" + 
	        		"    op.product_number,\n" + 
	        		"    op.product_name,\n" + 
	        		"    0 AS q1,\n" + 
	        		"    sum(op.q2) AS q2,\n" + 
	        		"    0 AS q3,\n" + 
	        		"    0 AS q4,\n" + 
	        		"    sum(op.total) AS total \n" + 
	        		"FROM\n" + 
	        		"    mstore_db.orders o\n" + 
	        		"        LEFT JOIN\n" + 
	        		"    (SELECT \n" + 
	        		"        ol.order_number,\n" + 
	        		"            ol.product_number,\n" + 
	        		"            p.product_name,\n" + 
	        		"            SUM(ol.quantity) AS q2,\n" + 
	        		"            sum(ol.quantity*p.price) AS total \n" + 
	        		"    FROM\n" + 
	        		"        mstore_db.order_line ol\n" + 
	        		"    LEFT JOIN mstore_db.product p ON ol.product_number = p.id\n" + 
	        		"    GROUP BY ol.order_number , ol.product_number , p.product_name) op ON o.order_number = op.order_number\n" + 
	        		"WHERE\n" + 
	        		"    YEAR(o.order_date) = 2019 AND 4 <= MONTH(o.order_date) AND MONTH(o.order_date) <= 6 \n" + 
	        		"    GROUP BY op.product_number , op.product_name, op.total, op.q2\n" + 
	        		"UNION SELECT \n" + 
	        		"    op.product_number,\n" + 
	        		"    op.product_name,\n" + 
	        		"    0 AS q1,\n" + 
	        		"    0 AS q2,\n" + 
	        		"    sum(op.q3) AS q3,\n" + 
	        		"    0 AS q4,\n" + 
	        		"    sum(op.total) AS total \n" + 
	        		"FROM\n" + 
	        		"    mstore_db.orders o\n" + 
	        		"        LEFT JOIN\n" + 
	        		"    (SELECT \n" + 
	        		"        ol.order_number,\n" + 
	        		"            ol.product_number,\n" + 
	        		"            p.product_name,\n" + 
	        		"            SUM(ol.quantity) AS q3,\n" + 
	        		"            sum(ol.quantity*p.price) AS total \n" + 
	        		"    FROM\n" + 
	        		"        mstore_db.order_line ol\n" + 
	        		"    LEFT JOIN mstore_db.product p ON ol.product_number = p.id\n" + 
	        		"    GROUP BY ol.order_number , ol.product_number , p.product_name) op ON o.order_number = op.order_number\n" + 
	        		"WHERE\n" + 
	        		"    YEAR(o.order_date) = 2019 AND 7 <= MONTH(o.order_date) AND MONTH(o.order_date) <= 9\n" + 
	        		"    GROUP BY op.product_number , op.product_name, op.total, op.q3\n" + 
	        		"UNION SELECT \n" + 
	        		"    op.product_number,\n" + 
	        		"    op.product_name,\n" + 
	        		"    0 AS q1,\n" + 
	        		"    0 AS q2,\n" + 
	        		"    0 AS q3,\n" + 
	        		"    sum(op.q4) AS q4,\n" + 
	        		"    sum(op.total) AS total \n" + 
	        		"FROM\n" + 
	        		"    mstore_db.orders o\n" + 
	        		"        LEFT JOIN\n" + 
	        		"    (SELECT \n" + 
	        		"        ol.order_number,\n" + 
	        		"            ol.product_number,\n" + 
	        		"            p.product_name,\n" + 
	        		"            SUM(ol.quantity) AS q4,\n" + 
	        		"            sum(ol.quantity*p.price) AS total \n" + 
	        		"    FROM\n" + 
	        		"        mstore_db.order_line ol\n" + 
	        		"    LEFT JOIN mstore_db.product p ON ol.product_number = p.id\n" + 
	        		"    GROUP BY ol.order_number , ol.product_number , p.product_name) op ON o.order_number = op.order_number\n" + 
	        		"WHERE\n" + 
	        		"    YEAR(o.order_date) = 2019 AND 10 <= MONTH(o.order_date) AND MONTH(o.order_date) <= 12 \n" + 
	        		"    GROUP BY op.product_number , op.product_name, op.total, op.q4) AS w\n" + 
	        		"GROUP BY item_id , item_name",
	        resultSetMapping="QuarterlyReportDataMapping"),
	@NamedNativeQuery(
	        name="ReportData.quarterlySalesByCategory",
	        query="SELECT \n" + 
	        		"	w.category_id AS item_id,\n" + 
	        		"    w.category_name AS item_name,\n" + 
	        		"    sum(w.q1) AS q1_sales,\n" + 
	        		"    sum(w.q2) AS q2_sales,\n" + 
	        		"    sum(w.q3) AS q3_sales,\n" + 
	        		"    sum(w.q4) AS q4_sales,\n" + 
	        		"    sum(w.q1 + w.q2 + w.q3 + w.q4) as total_sales,\n" + 
	        		"    sum(w.total) AS total \n" + 
	        		"FROM (SELECT \n" + 
	        		"    op.category_id,\n" + 
	        		"    op.category_name,\n" + 
	        		"    sum(op.q1) AS q1,\n" + 
	        		"    0 AS q2,\n" + 
	        		"    0 AS q3,\n" + 
	        		"    0 AS q4,\n" + 
	        		"    sum(op.total) AS total \n" + 
	        		"FROM\n" + 
	        		"    mstore_db.orders o\n" + 
	        		"        LEFT JOIN\n" + 
	        		"    (SELECT \n" + 
	        		"        ol.order_number,\n" + 
	        		"            pc.category_id,\n" + 
	        		"            pc.category_name,\n" + 
	        		"            SUM(ol.quantity) AS q1,\n" + 
	        		"            sum(ol.quantity*pc.price) AS total \n" + 
	        		"    FROM\n" + 
	        		"        mstore_db.order_line ol\n" + 
	        		"    LEFT JOIN (SELECT \n" + 
	        		"        p.category_id, c.category_name, p.price, p.id\n" + 
	        		"    FROM\n" + 
	        		"        mstore_db.product p\n" + 
	        		"    LEFT JOIN mstore_db.category c ON c.categoryid = p.category_id) pc ON ol.product_number = pc.id\n" + 
	        		"    GROUP BY ol.order_number , pc.category_id , pc.category_name) op ON o.order_number = op.order_number \n" + 
	        		"WHERE\n" + 
	        		"    YEAR(o.order_date) = :r_year AND 1 <= MONTH(o.order_date) AND MONTH(o.order_date) <= 3 \n" + 
	        		"    GROUP BY  op.category_id, op.category_name \n" + 
	        		"UNION SELECT \n" + 
	        		"    op.category_id,\n" + 
	        		"    op.category_name,\n" + 
	        		"    0 AS q1,\n" + 
	        		"    sum(op.q2) AS q2,\n" + 
	        		"    0 AS q3,\n" + 
	        		"    0 AS q4,\n" + 
	        		"    sum(op.total) AS total \n" + 
	        		"FROM\n" + 
	        		"    mstore_db.orders o\n" + 
	        		"        LEFT JOIN\n" + 
	        		"    (SELECT \n" + 
	        		"        ol.order_number,\n" + 
	        		"            pc.category_id,\n" + 
	        		"            pc.category_name,\n" + 
	        		"            SUM(ol.quantity) AS q2,\n" + 
	        		"            sum(ol.quantity*pc.price) AS total \n" + 
	        		"    FROM\n" + 
	        		"        mstore_db.order_line ol\n" + 
	        		"    LEFT JOIN (SELECT \n" + 
	        		"        p.category_id, c.category_name, p.price, p.id\n" + 
	        		"    FROM\n" + 
	        		"        mstore_db.product p\n" + 
	        		"    LEFT JOIN mstore_db.category c ON c.categoryid = p.category_id) pc ON ol.product_number = pc.id\n" + 
	        		"    GROUP BY ol.order_number , pc.category_id , pc.category_name) op ON o.order_number = op.order_number \n" + 
	        		"WHERE\n" + 
	        		"    YEAR(o.order_date) = :r_year AND 4 <= MONTH(o.order_date) AND MONTH(o.order_date) <= 6 \n" + 
	        		"    GROUP BY  op.category_id, op.category_name \n" + 
	        		"UNION SELECT \n" + 
	        		"    op.category_id,\n" + 
	        		"    op.category_name,\n" + 
	        		"    0 AS q1,\n" + 
	        		"    0 AS q2,\n" + 
	        		"    sum(op.q3) AS q3,\n" + 
	        		"    0 AS q4,\n" + 
	        		"    sum(op.total) AS total \n" + 
	        		"FROM\n" + 
	        		"    mstore_db.orders o\n" + 
	        		"        LEFT JOIN\n" + 
	        		"    (SELECT \n" + 
	        		"        ol.order_number,\n" + 
	        		"            pc.category_id,\n" + 
	        		"            pc.category_name,\n" + 
	        		"            SUM(ol.quantity) AS q3,\n" + 
	        		"            sum(ol.quantity*pc.price) AS total \n" + 
	        		"    FROM\n" + 
	        		"        mstore_db.order_line ol\n" + 
	        		"    LEFT JOIN (SELECT \n" + 
	        		"        p.category_id, c.category_name, p.price, p.id\n" + 
	        		"    FROM\n" + 
	        		"        mstore_db.product p\n" + 
	        		"    LEFT JOIN mstore_db.category c ON c.categoryid = p.category_id) pc ON ol.product_number = pc.id\n" + 
	        		"    GROUP BY ol.order_number , pc.category_id , pc.category_name) op ON o.order_number = op.order_number \n" + 
	        		"WHERE\n" + 
	        		"    YEAR(o.order_date) = :r_year AND 7 <= MONTH(o.order_date) AND MONTH(o.order_date) <= 9 \n" + 
	        		"    GROUP BY  op.category_id, op.category_name \n" + 
	        		"UNION SELECT \n" + 
	        		"    op.category_id,\n" + 
	        		"    op.category_name,\n" + 
	        		"    0 AS q1,\n" + 
	        		"    0 AS q2,\n" + 
	        		"    0 AS q3,\n" + 
	        		"    sum(op.q4) AS q4,\n" + 
	        		"    sum(op.total) AS total \n" + 
	        		"FROM\n" + 
	        		"    mstore_db.orders o\n" + 
	        		"        LEFT JOIN\n" + 
	        		"    (SELECT \n" + 
	        		"        ol.order_number,\n" + 
	        		"            pc.category_id,\n" + 
	        		"            pc.category_name,\n" + 
	        		"            SUM(ol.quantity) AS q4,\n" + 
	        		"            sum(ol.quantity*pc.price) AS total \n" + 
	        		"    FROM\n" + 
	        		"        mstore_db.order_line ol\n" + 
	        		"    LEFT JOIN (SELECT \n" + 
	        		"        p.category_id, c.category_name, p.price, p.id\n" + 
	        		"    FROM\n" + 
	        		"        mstore_db.product p\n" + 
	        		"    LEFT JOIN mstore_db.category c ON c.categoryid = p.category_id) pc ON ol.product_number = pc.id\n" + 
	        		"    GROUP BY ol.order_number , pc.category_id , pc.category_name) op ON o.order_number = op.order_number \n" + 
	        		"WHERE\n" + 
	        		"    YEAR(o.order_date) = :r_year AND 10 <= MONTH(o.order_date) AND MONTH(o.order_date) <= 12\n" + 
	        		"    GROUP BY  op.category_id, op.category_name) AS w\n" + 
	        		"GROUP BY item_id , item_name",
	        resultSetMapping="QuarterlyReportDataMapping"),
	@NamedNativeQuery(
	        name="ReportData.quarterlyOverallSales",
	        query="# Quarterly overall\n" + 
	        		"\n" + 
	        		"SELECT \n" + 
	        		"	sum(w.q1) AS q1_sales,\n" + 
	        		"    sum(w.q2) AS q2_sales,\n" + 
	        		"    sum(w.q3) AS q3_sales,\n" + 
	        		"    sum(w.q4) AS q4_sales,\n" + 
	        		"    sum(w.q1 + w.q2 + w.q3 + w.q4) as total_sales, \n" + 
	        		"    sum(w.total) as total   \n" + 
	        		"FROM (SELECT \n" + 
	        		"    sum(op.q1) AS q1,\n" + 
	        		"    0 AS q2,\n" + 
	        		"    0 AS q3,\n" + 
	        		"    0 AS q4,\n" + 
	        		"    sum(op.total) AS total \n" + 
	        		"FROM\n" + 
	        		"    mstore_db.orders o\n" + 
	        		"        LEFT JOIN\n" + 
	        		"    (SELECT \n" + 
	        		"        ol.order_number,\n" + 
	        		"            SUM(ol.quantity) AS q1,\n" + 
	        		"            sum(ol.quantity)*p.price AS total \n" + 
	        		"    FROM\n" + 
	        		"        mstore_db.order_line ol\n" + 
	        		"    LEFT JOIN mstore_db.product p ON ol.product_number = p.id\n" + 
	        		"    GROUP BY ol.order_number, p.price) op ON o.order_number = op.order_number\n" + 
	        		"WHERE\n" + 
	        		"	YEAR(o.order_date) = :r_year AND 1 <= MONTH(o.order_date) AND MONTH(o.order_date) <= 3\n" + 
	        		"UNION SELECT \n" + 
	        		"    0 AS q1,\n" + 
	        		"    sum(op.q2) AS q2,\n" + 
	        		"    0 AS q3,\n" + 
	        		"    0 AS q4,\n" + 
	        		"    sum(op.total) AS total \n" + 
	        		"FROM\n" + 
	        		"    mstore_db.orders o\n" + 
	        		"        LEFT JOIN\n" + 
	        		"    (SELECT \n" + 
	        		"        ol.order_number,\n" + 
	        		"            SUM(ol.quantity) AS q2,\n" + 
	        		"            sum(ol.quantity)*p.price AS total \n" + 
	        		"    FROM\n" + 
	        		"        mstore_db.order_line ol\n" + 
	        		"    LEFT JOIN mstore_db.product p ON ol.product_number = p.id\n" + 
	        		"    GROUP BY ol.order_number, p.price) op ON o.order_number = op.order_number\n" + 
	        		"WHERE\n" + 
	        		"    YEAR(o.order_date) = :r_year AND 4 <= MONTH(o.order_date) AND MONTH(o.order_date) <= 6\n" + 
	        		"UNION SELECT \n" + 
	        		"    0 AS q1,\n" + 
	        		"    0 AS q2,\n" + 
	        		"    sum(op.q3) AS q3,\n" + 
	        		"    0 AS q4,\n" + 
	        		"    sum(op.total) AS total \n" + 
	        		"FROM\n" + 
	        		"    mstore_db.orders o\n" + 
	        		"        LEFT JOIN\n" + 
	        		"    (SELECT \n" + 
	        		"        ol.order_number,\n" + 
	        		"            SUM(ol.quantity) AS q3,\n" + 
	        		"            sum(ol.quantity)*p.price AS total \n" + 
	        		"    FROM\n" + 
	        		"        mstore_db.order_line ol\n" + 
	        		"    LEFT JOIN mstore_db.product p ON ol.product_number = p.id\n" + 
	        		"    GROUP BY ol.order_number, p.price) op ON o.order_number = op.order_number\n" + 
	        		"WHERE\n" + 
	        		"    YEAR(o.order_date) = :r_year AND 7 <= MONTH(o.order_date) AND MONTH(o.order_date) <= 9\n" + 
	        		"UNION SELECT \n" + 
	        		"    0 AS q1,\n" + 
	        		"    0 AS q2,\n" + 
	        		"    0 AS q3,\n" + 
	        		"    sum(op.q4) AS q4,\n" + 
	        		"    sum(op.total) AS total \n" + 
	        		"FROM\n" + 
	        		"    mstore_db.orders o\n" + 
	        		"        LEFT JOIN\n" + 
	        		"    (SELECT \n" + 
	        		"        ol.order_number,\n" + 
	        		"            SUM(ol.quantity) AS q4,\n" + 
	        		"            sum(ol.quantity)*p.price AS total \n" + 
	        		"    FROM\n" + 
	        		"        mstore_db.order_line ol\n" + 
	        		"    LEFT JOIN mstore_db.product p ON ol.product_number = p.id\n" + 
	        		"    GROUP BY ol.order_number, p.price) op ON o.order_number = op.order_number\n" + 
	        		"WHERE\n" + 
	        		"    YEAR(o.order_date) = :r_year AND 10 <= MONTH(o.order_date) AND MONTH(o.order_date) <= 12) AS w",
	        resultSetMapping="QuarterlyOverallReportDataMapping"),
	@NamedNativeQuery(
	        name="ReportData.halfyearlySalesByProduct",
	        query="SELECT \n" + 
	        		"	w.product_number AS item_id,\n" + 
	        		"    w.product_name AS item_name,\n" + 
	        		"    sum(w.fh) AS fh_sales,\n" + 
	        		"    sum(w.sh) AS sh_sales,\n" + 
	        		"    sum(w.fh + w.sh) as total_sales, \n" + 
	        		"    sum(w.total) as total   \n" + 
	        		"FROM (SELECT \n" + 
	        		"    op.product_number,\n" + 
	        		"    op.product_name,\n" + 
	        		"    sum(op.fh) AS fh,\n" + 
	        		"    0 AS sh,\n" + 
	        		"    sum(op.total) AS total \n" + 
	        		"FROM\n" + 
	        		"    mstore_db.orders o\n" + 
	        		"        LEFT JOIN\n" + 
	        		"    (SELECT \n" + 
	        		"        ol.order_number,\n" + 
	        		"            ol.product_number,\n" + 
	        		"            p.product_name,\n" + 
	        		"            SUM(ol.quantity) AS fh,\n" + 
	        		"            sum(ol.quantity*p.price) AS total \n" + 
	        		"    FROM\n" + 
	        		"        mstore_db.order_line ol\n" + 
	        		"    LEFT JOIN mstore_db.product p ON ol.product_number = p.id\n" + 
	        		"    GROUP BY ol.order_number , ol.product_number , p.product_name) op ON o.order_number = op.order_number\n" + 
	        		"WHERE\n" + 
	        		"    YEAR(o.order_date) = 2019 AND 1 <= MONTH(o.order_date) AND MONTH(o.order_date) <= 6  \n" + 
	        		"    GROUP BY op.product_number , op.product_name, op.total, op.fh\n" + 
	        		"UNION SELECT \n" + 
	        		"    op.product_number,\n" + 
	        		"    op.product_name,\n" + 
	        		"    0 AS fh,\n" + 
	        		"    sum(op.sh) AS sh,\n" + 
	        		"    sum(op.total) AS total \n" + 
	        		"FROM\n" + 
	        		"    mstore_db.orders o\n" + 
	        		"        LEFT JOIN\n" + 
	        		"    (SELECT \n" + 
	        		"        ol.order_number,\n" + 
	        		"            ol.product_number,\n" + 
	        		"            p.product_name,\n" + 
	        		"            SUM(ol.quantity) AS sh,\n" + 
	        		"            sum(ol.quantity*p.price) AS total \n" + 
	        		"    FROM\n" + 
	        		"        mstore_db.order_line ol\n" + 
	        		"    LEFT JOIN mstore_db.product p ON ol.product_number = p.id\n" + 
	        		"    GROUP BY ol.order_number , ol.product_number , p.product_name) op ON o.order_number = op.order_number\n" + 
	        		"WHERE\n" + 
	        		"    YEAR(o.order_date) = 2019 AND 7 <= MONTH(o.order_date) AND MONTH(o.order_date) <= 12 \n" + 
	        		"    GROUP BY op.product_number , op.product_name, op.total, op.sh) AS w\n" + 
	        		"GROUP BY item_id , item_name",
	        resultSetMapping="HalfYearlyReportDataMapping"),
	@NamedNativeQuery(
	        name="ReportData.halfyearlySalesByCategory",
	        query="SELECT \n" + 
	        		"	w.category_id AS item_id,\n" + 
	        		"    w.category_name AS item_name,\n" + 
	        		"    sum(w.fh) AS fh_sales,\n" + 
	        		"    sum(w.sh) AS sh_sales,\n" + 
	        		"    sum(w.fh + w.sh) as total_sales,\n" + 
	        		"    sum(w.total) AS total \n" + 
	        		"FROM (SELECT \n" + 
	        		"    op.category_id,\n" + 
	        		"    op.category_name,\n" + 
	        		"    sum(op.fh) AS fh,\n" + 
	        		"    0 AS sh,\n" + 
	        		"    sum(op.total) AS total \n" + 
	        		"FROM\n" + 
	        		"    mstore_db.orders o\n" + 
	        		"        LEFT JOIN\n" + 
	        		"    (SELECT \n" + 
	        		"        ol.order_number,\n" + 
	        		"            pc.category_id,\n" + 
	        		"            pc.category_name,\n" + 
	        		"            SUM(ol.quantity) AS fh,\n" + 
	        		"            sum(ol.quantity*pc.price) AS total \n" + 
	        		"    FROM\n" + 
	        		"        mstore_db.order_line ol\n" + 
	        		"    LEFT JOIN (SELECT \n" + 
	        		"        p.category_id, c.category_name, p.price, p.id\n" + 
	        		"    FROM\n" + 
	        		"        mstore_db.product p\n" + 
	        		"    LEFT JOIN mstore_db.category c ON c.categoryid = p.category_id) pc ON ol.product_number = pc.id\n" + 
	        		"    GROUP BY ol.order_number , pc.category_id , pc.category_name) op ON o.order_number = op.order_number \n" + 
	        		"WHERE\n" + 
	        		"    YEAR(o.order_date) = 2019 AND 1 <= MONTH(o.order_date) AND MONTH(o.order_date) <= 6 \n" + 
	        		"    GROUP BY  op.category_id, op.category_name \n" + 
	        		"UNION SELECT \n" + 
	        		"    op.category_id,\n" + 
	        		"    op.category_name,\n" + 
	        		"    0 AS fh,\n" + 
	        		"    sum(op.sh) AS sh,\n" + 
	        		"    sum(op.total) AS total \n" + 
	        		"FROM\n" + 
	        		"    mstore_db.orders o\n" + 
	        		"        LEFT JOIN\n" + 
	        		"    (SELECT \n" + 
	        		"        ol.order_number,\n" + 
	        		"            pc.category_id,\n" + 
	        		"            pc.category_name,\n" + 
	        		"            SUM(ol.quantity) AS sh,\n" + 
	        		"            sum(ol.quantity*pc.price) AS total \n" + 
	        		"    FROM\n" + 
	        		"        mstore_db.order_line ol\n" + 
	        		"    LEFT JOIN (SELECT \n" + 
	        		"        p.category_id, c.category_name, p.price, p.id\n" + 
	        		"    FROM\n" + 
	        		"        mstore_db.product p\n" + 
	        		"    LEFT JOIN mstore_db.category c ON c.categoryid = p.category_id) pc ON ol.product_number = pc.id\n" + 
	        		"    GROUP BY ol.order_number , pc.category_id , pc.category_name) op ON o.order_number = op.order_number \n" + 
	        		"WHERE\n" + 
	        		"    YEAR(o.order_date) = 2019 AND 7 <= MONTH(o.order_date) AND MONTH(o.order_date) <= 12 \n" + 
	        		"    GROUP BY  op.category_id, op.category_name) AS w\n" + 
	        		"GROUP BY item_id , item_name",
	        resultSetMapping="HalfYearlyReportDataMapping"),
	@NamedNativeQuery(
	        name="ReportData.halfyearlyOverallSales",
	        query="SELECT \n" + 
	        		"	sum(w.fh) AS fh_sales,\n" + 
	        		"    sum(w.sh) AS sh_sales,\n" + 
	        		"    sum(w.fh + w.sh) as total_sales, \n" + 
	        		"    sum(w.total) as total   \n" + 
	        		"FROM (SELECT \n" + 
	        		"    sum(op.fh) AS fh,\n" + 
	        		"    0 AS sh,\n" + 
	        		"    sum(op.total) AS total \n" + 
	        		"FROM\n" + 
	        		"    mstore_db.orders o\n" + 
	        		"        LEFT JOIN\n" + 
	        		"    (SELECT \n" + 
	        		"        ol.order_number,\n" + 
	        		"            SUM(ol.quantity) AS fh,\n" + 
	        		"            sum(ol.quantity)*p.price AS total \n" + 
	        		"    FROM\n" + 
	        		"        mstore_db.order_line ol\n" + 
	        		"    LEFT JOIN mstore_db.product p ON ol.product_number = p.id\n" + 
	        		"    GROUP BY ol.order_number, p.price) op ON o.order_number = op.order_number\n" + 
	        		"WHERE\n" + 
	        		"	YEAR(o.order_date) = 2019 AND 1 <= MONTH(o.order_date) AND MONTH(o.order_date) <= 6\n" + 
	        		"UNION SELECT \n" + 
	        		"    0 AS fh,\n" + 
	        		"    sum(op.sh) AS sh,\n" + 
	        		"    sum(op.total) AS total \n" + 
	        		"FROM\n" + 
	        		"    mstore_db.orders o\n" + 
	        		"        LEFT JOIN\n" + 
	        		"    (SELECT \n" + 
	        		"        ol.order_number,\n" + 
	        		"            SUM(ol.quantity) AS sh,\n" + 
	        		"            sum(ol.quantity)*p.price AS total \n" + 
	        		"    FROM\n" + 
	        		"        mstore_db.order_line ol\n" + 
	        		"    LEFT JOIN mstore_db.product p ON ol.product_number = p.id\n" + 
	        		"    GROUP BY ol.order_number, p.price) op ON o.order_number = op.order_number\n" + 
	        		"WHERE\n" + 
	        		"    YEAR(o.order_date) = 2019 AND 7 <= MONTH(o.order_date) AND MONTH(o.order_date) <= 12) AS w",
	        resultSetMapping="HalfYearlyOverallReportDataMapping"),
	@NamedNativeQuery(
	        name="ReportData.annuallySalesByProduct",
	        query="SELECT \n" + 
	        		"    op.product_number AS item_id,\n" + 
	        		"    op.product_name AS item_name,\n" + 
	        		"    YEAR(o.order_date) AS year_sales,\n" + 
	        		"    sum(op.y_sales) AS total_sales,\n" + 
	        		"    sum(op.total) AS total \n" + 
	        		"FROM\n" + 
	        		"    mstore_db.orders o\n" + 
	        		"        LEFT JOIN\n" + 
	        		"    (SELECT \n" + 
	        		"        ol.order_number,\n" + 
	        		"            ol.product_number,\n" + 
	        		"            p.product_name,\n" + 
	        		"            SUM(ol.quantity) AS y_sales,\n" + 
	        		"            sum(ol.quantity*p.price) AS total \n" + 
	        		"    FROM\n" + 
	        		"        mstore_db.order_line ol\n" + 
	        		"    LEFT JOIN mstore_db.product p ON ol.product_number = p.id\n" + 
	        		"    GROUP BY ol.order_number , ol.product_number , p.product_name) op ON o.order_number = op.order_number\n" + 
	        		"WHERE\n" + 
	        		"    YEAR(o.order_date) = :r_year\n" + 
	        		"    GROUP BY item_id , item_name, year_sales",
	        resultSetMapping="AnnuallyReportDataMapping"),
	@NamedNativeQuery(
	        name="ReportData.annuallySalesByCategory",
	        query="SELECT \n" + 
	        		"    op.category_id AS item_id,\n" + 
	        		"    op.category_name AS item_name,\n" + 
	        		"    YEAR(o.order_date) AS year_sales,\n" + 
	        		"    sum(op.y_sales) AS total_sales,\n" + 
	        		"    sum(op.total) AS total \n" + 
	        		"FROM\n" + 
	        		"    mstore_db.orders o\n" + 
	        		"        LEFT JOIN\n" + 
	        		"    (SELECT \n" + 
	        		"        ol.order_number,\n" + 
	        		"            pc.category_id,\n" + 
	        		"            pc.category_name,\n" + 
	        		"            SUM(ol.quantity) AS y_sales,\n" + 
	        		"            sum(ol.quantity*pc.price) AS total \n" + 
	        		"    FROM\n" + 
	        		"        mstore_db.order_line ol\n" + 
	        		"    LEFT JOIN (SELECT \n" + 
	        		"        p.category_id, c.category_name, p.price, p.id\n" + 
	        		"    FROM\n" + 
	        		"        mstore_db.product p\n" + 
	        		"    LEFT JOIN mstore_db.category c ON c.categoryid = p.category_id) pc ON ol.product_number = pc.id\n" + 
	        		"    GROUP BY ol.order_number , pc.category_id , pc.category_name) op ON o.order_number = op.order_number \n" + 
	        		"WHERE\n" + 
	        		"    YEAR(o.order_date) = :r_year \n" + 
	        		"    GROUP BY item_id , item_name, year_sales",
	        resultSetMapping="AnnuallyReportDataMapping"),
	@NamedNativeQuery(
	        name="ReportData.annuallyOverallSales",
	        query="SELECT \n" + 
	        		"	YEAR(o.order_date) AS year_sales,\n" + 
	        		"    sum(op.y_sales) AS total_sales,\n" + 
	        		"    sum(op.total) AS total \n" + 
	        		"FROM\n" + 
	        		"    mstore_db.orders o\n" + 
	        		"        LEFT JOIN\n" + 
	        		"    (SELECT \n" + 
	        		"        ol.order_number,\n" + 
	        		"            SUM(ol.quantity) AS y_sales,\n" + 
	        		"            sum(ol.quantity*p.price) AS total \n" + 
	        		"    FROM\n" + 
	        		"        mstore_db.order_line ol\n" + 
	        		"    LEFT JOIN mstore_db.product p ON ol.product_number = p.id\n" + 
	        		"    GROUP BY ol.order_number, p.price) op ON o.order_number = op.order_number\n" + 
	        		"WHERE\n" + 
	        		"	YEAR(o.order_date) = 2019\n" + 
	        		"    GROUP BY year_sales",
	        resultSetMapping="AnnuallyOverallReportDataMapping"),
	@NamedNativeQuery(
	        name="ReportData.adhocSalesByProduct",
	        query="SELECT \n" + 
	        		"    op.product_number AS item_id,\n" + 
	        		"    op.product_name AS item_name,\n" + 
	        		"    o.order_date AS range_sales,\n" + 
	        		"    sum(op.r_sales) AS total_sales,\n" + 
	        		"    sum(op.total) AS total \n" + 
	        		"FROM\n" + 
	        		"    mstore_db.orders o\n" + 
	        		"        LEFT JOIN\n" + 
	        		"    (SELECT \n" + 
	        		"        ol.order_number,\n" + 
	        		"            ol.product_number,\n" + 
	        		"            p.product_name,\n" + 
	        		"            SUM(ol.quantity) AS r_sales,\n" + 
	        		"            sum(ol.quantity*p.price) AS total \n" + 
	        		"    FROM\n" + 
	        		"        mstore_db.order_line ol\n" + 
	        		"    LEFT JOIN mstore_db.product p ON ol.product_number = p.id\n" + 
	        		"    GROUP BY ol.order_number , ol.product_number , p.product_name) op ON o.order_number = op.order_number\n" + 
	        		"WHERE\n" + 
	        		"    o.order_date >= :startDate AND o.order_date <= :endDate \n" + 
	        		"    GROUP BY item_id , item_name, range_sales",
	        resultSetMapping="AdhocReportDataMapping"),
	@NamedNativeQuery(
	        name="ReportData.adhocSalesByCategory",
	        query="SELECT \n" + 
	        		"    op.category_id AS item_id,\n" + 
	        		"    op.category_name AS item_name,\n" + 
	        		"    o.order_date AS range_sales,\n" + 
	        		"    sum(op.r_sales) AS total_sales,\n" + 
	        		"    sum(op.total) AS total \n" + 
	        		"FROM\n" + 
	        		"    mstore_db.orders o\n" + 
	        		"        LEFT JOIN\n" + 
	        		"    (SELECT \n" + 
	        		"        ol.order_number,\n" + 
	        		"            pc.category_id,\n" + 
	        		"            pc.category_name,\n" + 
	        		"            SUM(ol.quantity) AS r_sales,\n" + 
	        		"            sum(ol.quantity*pc.price) AS total \n" + 
	        		"    FROM\n" + 
	        		"        mstore_db.order_line ol\n" + 
	        		"    LEFT JOIN (SELECT \n" + 
	        		"        p.category_id, c.category_name, p.price, p.id\n" + 
	        		"    FROM\n" + 
	        		"        mstore_db.product p\n" + 
	        		"    LEFT JOIN mstore_db.category c ON c.categoryid = p.category_id) pc ON ol.product_number = pc.id\n" + 
	        		"    GROUP BY ol.order_number , pc.category_id , pc.category_name) op ON o.order_number = op.order_number \n" + 
	        		"WHERE\n" + 
	        		"    o.order_date >= :startDate AND o.order_date <= :endDate \n" + 
	        		"    GROUP BY item_id , item_name, range_sales",
	        resultSetMapping="AdhocReportDataMapping"),
	@NamedNativeQuery(
	        name="ReportData.adhocOverallSales",
	        query="SELECT \n" + 
	        		"	 o.order_date AS range_sales,\n" + 
	        		"    sum(op.r_sales) AS total_sales,\n" + 
	        		"    sum(op.total) AS total \n" + 
	        		"FROM\n" + 
	        		"    mstore_db.orders o\n" + 
	        		"        LEFT JOIN\n" + 
	        		"    (SELECT \n" + 
	        		"        ol.order_number,\n" + 
	        		"            SUM(ol.quantity) AS r_sales,\n" + 
	        		"            sum(ol.quantity*p.price) AS total \n" + 
	        		"    FROM\n" + 
	        		"        mstore_db.order_line ol\n" + 
	        		"    LEFT JOIN mstore_db.product p ON ol.product_number = p.id\n" + 
	        		"    GROUP BY ol.order_number, p.price) op ON o.order_number = op.order_number\n" + 
	        		"WHERE\n" + 
	        		"	o.order_date >= :startDate AND o.order_date <= :endDate \n" + 
	        		"    GROUP BY range_sales",
	        resultSetMapping="AdhocOverallReportDataMapping")
})
public class ReportData {
		
	@Id
	private Integer id;

}