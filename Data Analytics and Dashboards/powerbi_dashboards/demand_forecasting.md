// Report for historical demand and AI-driven forecasting
*Description:*  
Forecasts future ride demand using historical data and integrates AI-driven insights.

**Report Contents Specification:**

- **KPIs & Measures:**  
  - **Historical Ride Demand:** Trend line from historical data.
  - **Forecasted Demand:** Use Power BI forecasting or integrate Azure ML output.
  - **Forecast Accuracy:** Calculated measure comparing forecast vs. actual.
  
- **Visuals:**  
  - Line chart with forecasting overlay  
  - KPI card for forecast accuracy  
  - Geo-visualization of demand shifts
  
*Implementation:*  
Construct the report in Power BI Desktop, using built-in forecasting features and DAX measures, then save as `demand_forecasting.pbix`.

```mermaid
%%{init: {'theme': 'base', 'themeVariables': { 'primaryColor': '#f4f4f4'}}}%%
flowchart TD
    subgraph Data_Sources
        A[Historical Rides]
        B[Ride Requests]
    end

    A --> ETL
    B --> ETL

    subgraph ETL_Process
        ETL[[ETL Pipeline]] -->|Cleaned Data| DW[(Data Warehouse)]
    end

    subgraph DAX_Calculations
        DW --> HR["Historical Demand: Trend Line"]
        DW --> FD["Forecasted Demand: Forecast Measure"]
        DW --> FA["Forecast Accuracy: (Actual vs Forecast)"]
    end

    subgraph Visualizations
        HR --> V1[Line Chart]
        FD --> V1
        FA --> V2[KPI Card]
        DW --> V3[Geo-Visual: Demand Shifts]
    end

    subgraph Output
        V1 --> DASHBOARD[[Demand Forecasting Dashboard]]
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
    class DW,HR,FD,FA calc
    class V1,V2,V3 viz
    class DASHBOARD output

```
