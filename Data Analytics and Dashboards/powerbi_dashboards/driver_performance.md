// Report for driver metrics, ratings, and earnings
*Description:*  
Tracks driver performance, including total rides per driver, ratings, earnings, and geographic coverage.

**Report Contents Specification:**

- **KPIs & Measures:**  
  - **Total Rides per Driver:**  
    ```DAX
    RidesPerDriver = COUNTROWS('Bookings')
    ```
    (Filtered by driver_id.)  
  - **Average Driver Rating:**  
    ```DAX
    AvgDriverRating = AVERAGE('Driver_Ratings'[rating])
    ```
  - **Driver Earnings:** Aggregate earnings from a dedicated payments table.
  
- **Visuals:**  
  - Bar chart or table visual for top drivers by rides and earnings  
  - Scorecards for average ratings  
  - Map visual showing driver locations (if available)

*Implementation:*  
Configure the visuals and DAX measures in Power BI Desktop; then save as `driver_performance.pbix`.

```mermaid
%%{init: {'theme': 'base', 'themeVariables': { 'primaryColor': '#f4f4f4'}}}%%
flowchart TD
    subgraph Data_Sources
        A[Driver Records]
        B[Bookings]
        C[Driver Ratings]
        D[Payments]
    end

    A --> ETL 
    B --> ETL 
    C --> ETL 
    D --> ETL 

    subgraph ETL_Process 
      ETL[[ETL Pipeline]]-->DW[(Data Warehouse)]
    end 

   subgraph DAX_Calculations 
      DW-->TRD["Total Rides per Driver: COUNTROWS(Bookings)"]
      DW-->ADR["Avg Driver Rating: AVERAGE(Driver_Ratings[rating])"]
      DW-->DE["Driver Earnings: SUM(Payments[amount])"] 
   end 

   subgraph Visualizations 
      TRD-->V1[Bar Chart/Table - Top Drivers] 
      ADR-->V2[Scorecards - Ratings] 
      DE-->V1 
      DW-->V3[Map - Driver Locations] 
   end 

   subgraph Output 
      V1-->DASHBOARD[[Driver Performance Dashboard]] 
      V2-->DASHBOARD 
      V3-->DASHBOARD 
   end 

   classDef source fill:#c8e6c9,stroke:#2e7d32 
   classDef etl fill:#bbdefb,stroke:#1976d2 
   classDef calc fill:#fff9c4,stroke:#f9a825 
   classDef viz fill:#f8bbd0,stroke:#d81b60 
   classDef output fill:#e1bee7,stroke:#8e24aa 

   class A,B,C,D source
   class ETL etl
   class DW,TRD,ADR,DE calc
   class V1,V2,V3 viz
   class DASHBOARD output

```
