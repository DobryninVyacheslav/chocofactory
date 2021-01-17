package ru.mileev.chocofactory.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
class ReportServiceTest {

    @InjectMocks
    ReportService service;
    @Mock
    BatchService batchService;

    @Test
    void whenPrepareReportDataThenInvokeBatchServiceMethod() {
        // Arrange
        LocalDate before = LocalDate.parse("2020-01-15");
        LocalDate after = LocalDate.parse("2020-01-20");

        // Act
        service.prepareReportData(before, after);

        // Assert
        verify(batchService, times(1))
                .findAllByCreationDateBetween(before, after);
    }

}