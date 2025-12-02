package nextstep.qna.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DeleteHistories {
    
    private List<DeleteHistory> deleteHistories = new ArrayList<>();
    
    public DeleteHistories(DeleteHistory... deleteHistories) {
        this(Arrays.asList(deleteHistories));
    }
    
    public DeleteHistories(List<DeleteHistory> deleteHistories) {
        this.deleteHistories.addAll(deleteHistories);
    }
    
    public void addHistories(DeleteHistories deleteHistories) {
        addHistories(deleteHistories.deleteHistories);
    }
    
    public void addHistories(List<DeleteHistory> deleteHistories) {
        this.deleteHistories.addAll(deleteHistories);
    }
    
    public void addHistories(DeleteHistory deleteHistories) {
        this.deleteHistories.add(deleteHistories);
    }
    
    public List<DeleteHistory> toList() {
        return this.deleteHistories;
    }
}