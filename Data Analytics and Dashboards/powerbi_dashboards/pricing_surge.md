// Report for surge pricing, fare fluctuations, and revenue impact
*Description:*  
Monitors surge pricing activations, fare fluctuations, and the revenue impact of surge pricing.

**Report Contents Specification:**

- **KPIs & Measures:**  
  - **Surge Activation Count:**  
    ```DAX
    SurgeActivations = COUNTROWS('Surge_Logs')
    ```
  - **Revenue Impact from Surge:**  
    ```DAX
    RevenueImpact = SUMX('Fares', ('Fares'[total_fare] - ('Fares'[base_fare] + 'Fares'[distance_charge] + 'Fares'[waiting_charge])))
    ```
  - **Average Surge Multiplier:**  
    ```DAX
    AvgSurge = AVERAGE('Fares'[surge_multiplier])
    ```
  
- **Visuals:**  
  - Time series chart of surge multipliers over time  
  - Heatmap of regions with high surge values  
  - Waterfall chart showing fare breakdown

*Implementation:*  
Design these visuals and measures in Power BI Desktop and save as `pricing_surge.pbix`.

```mermaid
%%{init: {'theme': 'base', 'themeVariables': { 'primaryColor': '#f4f4f4'}}}%%
flowchart TD
    subgraph Data_Sources
        A[Surge Logs]
        B[Fares]
    end

    A --> ETL 
    B --> ETL 

   subgraph ETL_Process 
      ETL[[ETL Pipeline]]-->DW[(Data Warehouse)] 
   end 

   subgraph DAX_Calculations 
      DW-->SAC["Surge Activation Count: COUNTROWS(Surge Logs)"] 
      DW-->RI["Revenue Impact: SUMX(Fares,total_fare - (base_fare + distance_charge + waiting_charge))"] 
      DW-->ASM["Avg Surge Multiplier: AVERAGE(Fares[surge_multiplier])"] 
   end 

   subgraph Visualizations 
      SAC-->V1[Time Series Chart] 
      RI-->V2[Waterfall Chart] 
      ASM-->V1 
      DW-->V3[Heatmap - High Surge Regions] 
   end 

   subgraph Output 
      V1-->DASHBOARD[[Pricing Surge Dashboard]] 
      V2-->DASHBOARD 
      V3-->DASHBOARD 
   end 

   classDef source fill:#c8e6c9,stroke:#2e7d32 
   classDef etl fill:#bbdefb,stroke:#1976d2 
   classDef calc fill:#fff9c4,stroke:#f9a825 
   classDef viz fill:#f8bbd0,stroke:#d81b60 
   classDef output fill:#e1bee7,stroke:#8e24aa

   class A,B source
   class ETL etl
   class DW,SAC,RI,ASM calc
   class V1,V2,V3 viz
   class DASHBOARD output
```
