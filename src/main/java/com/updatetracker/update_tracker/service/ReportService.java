package com.updatetracker.update_tracker.service;
import com.updatetracker.update_tracker.model.WorkUpdate; import com.updatetracker.update_tracker.repository.WorkUpdateRepository; import org.springframework.stereotype.Service; import java.time.LocalDate; import java.util.*; import java.util.stream.Collectors;
@Service public class ReportService { private final WorkUpdateRepository repo; public ReportService(WorkUpdateRepository r){repo=r;}
 public Map<String,Long> countByUser(){return repo.search(null,null,null,null).stream().collect(Collectors.groupingBy(x->x.getUser().getUsername(),LinkedHashMap::new,Collectors.counting()));}
 public Map<String,Long> countByProject(){return repo.search(null,null,null,null).stream().collect(Collectors.groupingBy(x->x.getProject().getName(),LinkedHashMap::new,Collectors.counting()));}
}
