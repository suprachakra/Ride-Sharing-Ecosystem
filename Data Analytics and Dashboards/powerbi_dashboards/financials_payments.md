// Report for revenue, payment success, refund rates, and commission breakdown
*Description:*  
Tracks financial metrics including revenue, payouts, refund rates, and payment method performance.

**Report Contents Specification:**

- **KPIs & Measures:**  
  - **Total Revenue:**  
    ```DAX
    TotalRevenue = SUM('Fares'[total_fare])
    ```
  - **Total Payouts to Drivers:** (Assume a related table or calculation)
  - **Refund Rate:**  
    ```DAX
    RefundRate = DIVIDE(CALCULATE(COUNTROWS('Payments'), 'Payments'[status] = "REFUNDED"), COUNTROWS('Payments'))
    ```
  - **Payment Success Rate:**  
    ```DAX
    PaymentSuccess = DIVIDE(CALCULATE(COUNTROWS('Payments'), 'Payments'[status] = "SUCCESS"), COUNTROWS('Payments'))
    ```
  
- **Visuals:**  
  - Pie charts for distribution of payment methods  
  - Bar charts comparing revenue and payouts  
  - Tables showing detailed payment transactions

*Implementation:*  
Develop the report in Power BI Desktop; then save as `financials_payments.pbix`.

```mermaid
%%{init: {'theme': 'base', 'themeVariables': { 'primaryColor': '#f4f4f4'}}}%%
flowchart TD
    subgraph Data_Sources
        A[Fares]
        B[Payments]
    end

    A --> ETL
    B --> ETL

    subgraph ETL_Process
        ETL[[ETL Pipeline]] -->|Cleaned Data| DW[(Data Warehouse)]
    end

    subgraph DAX_Calculations
        DW --> TRF["Total Revenue: SUM(Fares[total_fare])"]
        DW --> TP["Total Payouts: SUM(Payments[payout_amount])"]
        DW --> RR["Refund Rate: DIVIDE(COUNTROWS(Filter(Payments,status='REFUNDED')), COUNTROWS(Payments))"]
        DW --> PSR["Payment Success Rate: DIVIDE(COUNTROWS(Filter(Payments,status='SUCCESS')), COUNTROWS(Payments))"]
    end

    subgraph Visualizations
        TRF --> V1[Bar Charts - Revenue vs Payouts]
        RR --> V2[Pie Charts - Refund Rates]
        PSR --> V2
        DW --> V3[Detailed Transaction Tables]
    end

    subgraph Output
        V1 --> DASHBOARD[[Financials & Payments Dashboard]]
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
    class DW,TRF,TP,RR,PSR calc
    class V1,V2,V3 viz
    class DASHBOARD output
```
