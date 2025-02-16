// Report for API uptime, latency, crash rates, and system health
*Description:*  
Provides insights into system performance, including API response times, system uptime, and crash rates.

**Report Contents Specification:**

- **KPIs & Measures:**  
  - **API Response Time:**  
    ```DAX
    AvgResponseTime = AVERAGE('OperationalMetrics'[response_time])
    ```
    (Assumes an OperationalMetrics table exists)
  - **System Uptime:** Calculated from logs.
  - **Crash Rate:** Derived from error logs.
  
- **Visuals:**  
  - Time series charts for API latency  
  - Card visuals for uptime percentage  
  - Dashboard of error rate trends

*Implementation:*  
Set up in Power BI Desktop with the provided measures; then save as `operational_efficiency.pbix`.

```mermaid
%%{init: {'theme': 'base', 'themeVariables': { 'primaryColor': '#f4f4f4'}}}%%
flowchart TD
    subgraph Data_Sources
        A[Operational Metrics]
        B[Error Logs]
    end

    A --> ETL
    B --> ETL

    subgraph ETL_Process
        ETL[[ETL Pipeline]] -->|Cleaned Data| DW[(Data Warehouse)]
    end

    subgraph DAX_Calculations
        DW --> ART["Avg Response Time: AVERAGE(Operational_Metrics[response_time])"]
        DW --> SU["System Uptime: Derived Measure"]
        DW --> CR["Crash Rate: COUNTROWS(Error Logs)"]
    end

    subgraph Visualizations
        ART --> V1[Line Charts - API Response Time]
        SU --> V2[KPI Cards - Uptime %]
        CR --> V3[Trend Dashboard - Crash Rates]
    end

    subgraph Output
        V1 --> DASHBOARD[[Operational Efficiency Dashboard]]
        V2 --> DASHBOARD
        V3 --> DASHBOARD
    end

    classDef source fill:#c8e6c9,stroke:#2e7d32
    classDef etl fill:#bbdefb,stroke:#1976d2
    classDef calc fill:#fff9c4,stroke:#f9a825
    classDef viz fill:#f8bbd0,stroke:#d81b60
    classDef output fill:#e1bee7,stroke:#8e24aa

    class A,B source
    class ETL etl
    class DW,ART,SU,CR calc
    class V1,V2,V3 viz
    class DASHBOARD output

```
