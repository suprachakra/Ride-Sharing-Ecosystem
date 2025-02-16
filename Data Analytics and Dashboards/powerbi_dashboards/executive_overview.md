// Report file for high-level KPIs and business health

*Description:*  
This Power BI report provides an executive view of the overall business health. It includes KPI cards, trend lines, and geo-heatmaps.

**Report Contents Specification:**

- **Data Model:**  
  - Tables: USERS, DRIVERS, BOOKINGS, FARES, PAYMENTS, RIDE_METRICS, etc. (imported from your data warehouse)  
- **KPIs:**  
  - **Total Rides Completed:**  
    ```DAX
    TotalRides = COUNTROWS('Bookings')
    ```
  - **Total Revenue:**  
    ```DAX
    TotalRevenue = SUM('Fares'[total_fare])
    ```
  - **Average Ride Fare:**  
    ```DAX
    AvgFare = AVERAGE('Fares'[total_fare])
    ```
  - **Cancellation Rate:**  
    ```DAX
    CancellationRate = DIVIDE(CALCULATE(COUNTROWS('Bookings'), 'Bookings'[status] = "CANCELLED"), COUNTROWS('Bookings'))
    ```
  - **Active Users:** Count distinct users from BOOKINGS (rider_id).  

- **Visuals:**  
  - KPI Tiles (cards) for Total Rides, Total Revenue, Avg Fare, Cancellation Rate  
  - Time series charts (line charts) for daily and weekly trends  
  - A geo-heatmap showing ride distribution by region  

*Implementation:*  
Create these visuals in Power BI Desktop using the above DAX measures, format them in a clean layout, and save as `executive_overview.pbix`.

```mermaid
%%{init: {'theme': 'base', 'themeVariables': { 'primaryColor': '#f4f4f4'}}}%%
flowchart TD
    subgraph Data_Sources
        A[USERS] --> ETL
        B[DRIVERS] --> ETL
        C[BOOKINGS] --> ETL
        D[FARES] --> ETL
        E[RIDE_METRICS] --> ETL
    end

    subgraph ETL_Process
        ETL[[ETL Pipeline]] -->|Cleaned Data| DW[(Data Warehouse)]
    end

    subgraph DAX_Calculations
        DW --> TR["Total Rides: COUNTROWS('Bookings')"]
        DW --> RV["Total Revenue: SUM('Fares'[total_fare])"]
        DW --> AF["Avg Fare: AVERAGE('Fares'[total_fare])"]
        DW --> CR["Cancellation Rate: DIVIDE(CANCELLED, TOTAL)"]
        DW --> AU["Active Users: DISTINCTCOUNT(rider_id)"]
    end

    subgraph Visualizations
        TR --> V1[KPI Cards]
        RV --> V1
        AF --> V1
        CR --> V1
        
        AU --> V2[Time Series Charts]
        CR --> V2
        
        DW --> V3[Geo-Heatmap]
    end

    subgraph Output
        V1 --> DASHBOARD[[Executive Dashboard]]
        V2 --> DASHBOARD
        V3 --> DASHBOARD
    end

    classDef source fill:#c8e6c9,stroke:#2e7d32
    classDef etl fill:#bbdefb,stroke:#1976d2
    classDef calc fill:#fff9c4,stroke:#f9a825
    classDef viz fill:#f8bbd0,stroke:#d81b60
    classDef output fill:#e1bee7,stroke:#8e24aa
    
    class A,B,C,D,E source
    class ETL etl
    class DW,TR,RV,AF,CR,AU calc
    class V1,V2,V3 viz
```
