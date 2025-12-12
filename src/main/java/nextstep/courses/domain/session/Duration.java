package nextstep.courses.domain.session;

import java.time.LocalDate;
import nextstep.courses.CanNotCreateException;

public class Duration {
    
    private final LocalDate startDate;
    private final LocalDate endDate;
    
    public Duration(String startDate, String endDate) {
        this.startDate = LocalDate.parse(startDate);
        this.endDate = LocalDate.parse(endDate);
    }
    
    public Duration(LocalDate startDate, LocalDate endDate) throws CanNotCreateException {
        validate(startDate, endDate);
        this.startDate = startDate;
        this.endDate = endDate;
    }
    
    private void validate(LocalDate startDate, LocalDate endDate) throws CanNotCreateException {
        if(isBeforeDateThenCurrentDate(startDate, endDate)) {
            throw new CanNotCreateException("강의 날짜는 오늘에서 다음날에 존재해야 한다");
        }
        if(isEndDateEarly(startDate, endDate)) {
            throw new CanNotCreateException("강의 종료일이 시작일보다 앞에 있을 수 없다");
        }
        
    }
    
    private static boolean isEndDateEarly(LocalDate startDate, LocalDate endDate) {
        return endDate.isBefore(startDate);
    }
    
    private static boolean isBeforeDateThenCurrentDate(LocalDate startDate, LocalDate endDate) {
        LocalDate currentDate = LocalDate.now();
        return startDate.isBefore(currentDate) || endDate.isBefore(currentDate);
    }
}