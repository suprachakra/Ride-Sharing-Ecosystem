// Report for ride completion rates, pickup times, and demand heatmaps
*Description:*  
Focuses on ride metrics such as ride requests versus completions, average pickup times, and demand heatmaps.

**Report Contents Specification:**

- **KPIs & Measures:**  
  - **Rides Requested vs Completed:**  
    ```DAX
    RidesRequested = COUNTROWS('Booking_Requests')
    ```
    (Assume you have a staging table for ride requests.)  
  - **Average Pickup Time:**  
    ```DAX
    AvgPickupTime = AVERAGE('Bookings'[pickup_time] - 'Bookings'[created_at])
    ```
  - **Peak Hours & Demand:**  
    Create a calculated column for hour-of-day and then aggregate rides per hour.
  
- **Visuals:**  
  - Bar charts comparing ride requests and completions  
  - Line chart for average pickup time over the day  
  - Heatmap showing high-demand areas (using geographic data)

*Implementation:*  
Build these visuals in Power BI Desktop with the appropriate DAX measures, then save as `ride_performance.pbix`.

```mermaid
%%{init: {'theme': 'base', 'themeVariables': { 'primaryColor': '#f4f4f4'}}}%%
flowchart TD
    subgraph Data_Sources
        A[Bookings]
        B[Fares]
        C[Ride Metrics]
    end

    A --> ETL
    B --> ETL
    C --> ETL

    subgraph ETL_Process
        ETL[[ETL Pipeline]] -->|Cleaned Data| DW[(Data Warehouse)]
    end

    subgraph DAX_Calculations
        DW --> TR["Total Rides: COUNTROWS('Bookings')"]
        DW --> RV["Total Revenue: SUM('Fares'[total_fare])"]
        DW --> AF["Avg Fare: AVERAGE('Fares'[total_fare])"]
        DW --> CR["Cancellation Rate: DIVIDE(Count(CANCELLED), Count(TOTAL))"]
        DW --> AU["Active Users: DISTINCTCOUNT(rider_id)"]
    end

    subgraph Visualizations
        TR --> V1[KPI Cards]
        RV --> V1
        AF --> V1
        CR --> V1
        AU --> V2[Time Series Charts]
        DW --> V3[Geo-Heatmap]
    end

    subgraph Output
        V1 --> DASHBOARD[[Ride Performance Dashboard]]
        V2 --> DASHBOARD
        V3 --> DASHBOARD
    end

    classDef source fill:#c8e6c9,stroke:#2e7d32
    classDef etl fill:#bbdefb,stroke:#1976d2
    classDef calc fill:#fff9c4,stroke:#f9a825
    classDef viz fill:#f8bbd0,stroke:#d81b60
    classDef output fill:#e1bee7,stroke:#8e24aa

    class A,B,C source
    class ETL etl
    class DW,TR,RV,AF,CR,AU calc
    class V1,V2,V3 viz
    class DASHBOARD output
```
