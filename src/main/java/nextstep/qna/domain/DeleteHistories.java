package nextstep.qna.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DeleteHistories {
    
    private List<DeleteHistory> deleteHistories = new ArrayList<>();
    
    public DeleteHistories(DeleteHistory... deleteHistories) {
        this(Arrays.asList(deleteHistories));
    }
    
    public DeleteHistories(DeleteHistory deleteHistory, List<DeleteHistory> deleteHistories) {
        this (Stream.concat(Stream.of(deleteHistory), deleteHistories.stream()).collect(Collectors.toList()));
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