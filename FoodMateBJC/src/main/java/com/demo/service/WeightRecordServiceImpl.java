package com.demo.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.domain.MemberData;
import com.demo.domain.WeightRecord;
import com.demo.persistence.WeightRecordRepository;

@Service
public class WeightRecordServiceImpl implements WeightRecordService {

	@Autowired
	private WeightRecordRepository weightRecordRepo;

	@Override
    public void saveWeightRecord(WeightRecord weightRecord) {
        weightRecordRepo.save(weightRecord);
    }
	
	// 회원별 체중 기록 조회
	@Override
    public List<WeightRecord> getWeightRecordsByMemberId(String id) {
        // 회원 ID를 기준으로 해당 회원의 체중 기록을 가져옵니다.
        MemberData member = new MemberData();
        member.setId(id);
        return weightRecordRepo.findByMember(member);
    }

	// 최근 7일의 체중 기록을 반환하는 메서드
	@Override
	public List<WeightRecord> getRecentWeekRecords(String id) {
		// 현재 날짜를 기준으로 7일 전의 날짜를 계산
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -7);
        Date startDate = cal.getTime();

        return weightRecordRepo.getRecord7ById(id, startDate);
	}

	// 최근 30일의 체중 기록을 반환하는 메서드
	@Override
	public List<WeightRecord> getRecentMonthRecords(String id) {
		// 현재 날짜를 기준으로 30일 전의 날짜를 계산
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -30);
        Date startDate = cal.getTime();

        return weightRecordRepo.getRecord30ById(id, startDate);
	}

	// 주간/월간 변화 평균값 계산 메서드
	@Override
	public double calculateAverageWeight(List<WeightRecord> records) {
		if(records.isEmpty()) {
			return 0.0;
		}
		double sum = 0.0;
        for (WeightRecord record : records) {
            sum += record.getRe_weight();
        }
        return sum / records.size();
	}

	@Override
	public List<WeightRecord> getWeirghtRecordFiltering(String id, Date startDate, Date endDate) {
		return weightRecordRepo.findByMemberIdAndCreatedAtBetween(id, startDate, endDate);
	}
	
	
}
