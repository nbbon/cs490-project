package mum.pmp.mstore.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import mum.pmp.mstore.service.report.AdhocOverallReportData;
import mum.pmp.mstore.service.report.AdhocReportData;
import mum.pmp.mstore.service.report.AnnuallyOverallReportData;
import mum.pmp.mstore.service.report.AnnuallyReportData;
import mum.pmp.mstore.service.report.HalfYearlyOverallReportData;
import mum.pmp.mstore.service.report.HalfYearlyReportData;
import mum.pmp.mstore.service.report.MonthlyOverallReportData;
import mum.pmp.mstore.service.report.MonthlyReportData;
import mum.pmp.mstore.service.report.QuarterlyOverallReportData;
import mum.pmp.mstore.service.report.QuarterlyReportData;
import mum.pmp.mstore.service.report.ReportData;
import mum.pmp.mstore.service.report.WeeklyOverallReportData;
import mum.pmp.mstore.service.report.WeeklyReportData;

@Repository
public interface ReportRepository extends CrudRepository<ReportData, Long> {
	
	@Query(nativeQuery = true)
	List<WeeklyReportData> weeklySalesByProduct(@Param("d1") LocalDate d1, @Param("d2") LocalDate d2, @Param("d3") LocalDate d3,
			@Param("d4") LocalDate d4, @Param("d5") LocalDate d5, @Param("d6") LocalDate d6, @Param("d7") LocalDate d7);
	
	@Query(nativeQuery = true)
	List<WeeklyReportData> weeklySalesByCategory(@Param("d1") LocalDate d1, @Param("d2") LocalDate d2, @Param("d3") LocalDate d3,
			@Param("d4") LocalDate d4, @Param("d5") LocalDate d5, @Param("d6") LocalDate d6, @Param("d7") LocalDate d7);

	@Query(nativeQuery = true)
	List<WeeklyOverallReportData> weeklyOverallSales(@Param("d1") LocalDate d1, @Param("d2") LocalDate d2, @Param("d3") LocalDate d3,
			@Param("d4") LocalDate d4, @Param("d5") LocalDate d5, @Param("d6") LocalDate d6, @Param("d7") LocalDate d7);
	
	@Query(nativeQuery = true)
	List<MonthlyReportData> monthlySalesByProduct(@Param("r_year") Integer r_year);

	@Query(nativeQuery = true)
	List<MonthlyReportData> monthlySalesByCategory(@Param("r_year") Integer r_year);

	@Query(nativeQuery = true)
	List<MonthlyOverallReportData> monthlyOverallSales(@Param("r_year") Integer r_year);
	
	@Query(nativeQuery = true)
	List<QuarterlyReportData> quarterlySalesByProduct(@Param("r_year") Integer r_year);

	@Query(nativeQuery = true)
	List<QuarterlyReportData> quarterlySalesByCategory(@Param("r_year") Integer r_year);

	@Query(nativeQuery = true)
	List<QuarterlyOverallReportData> quarterlyOverallSales(@Param("r_year") Integer r_year);
	
	@Query(nativeQuery = true)
	List<HalfYearlyReportData> halfyearlySalesByProduct(@Param("r_year") Integer r_year);

	@Query(nativeQuery = true)
	List<HalfYearlyReportData> halfyearlySalesByCategory(@Param("r_year") Integer r_year);

	@Query(nativeQuery = true)
	List<HalfYearlyOverallReportData> halfyearlyOverallSales(@Param("r_year") Integer r_year);

	@Query(nativeQuery = true)
	List<AnnuallyReportData> annuallySalesByProduct(@Param("r_year") Integer r_year);

	@Query(nativeQuery = true)
	List<AnnuallyReportData> annuallySalesByCategory(@Param("r_year") Integer r_year);

	@Query(nativeQuery = true)
	List<AnnuallyOverallReportData> annuallyOverallSales(@Param("r_year") Integer r_year);

	@Query(nativeQuery = true)
	List<AdhocReportData> adhocSalesByProduct(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

	@Query(nativeQuery = true)
	List<AdhocReportData> adhocSalesByCategory(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

	@Query(nativeQuery = true)
	List<AdhocOverallReportData> adhocOverallSales(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

}
