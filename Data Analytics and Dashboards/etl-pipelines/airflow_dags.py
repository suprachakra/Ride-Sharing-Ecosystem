// Sample Airflow DAGs for scheduled ETL jobs

from airflow import DAG
from airflow.operators.bash import BashOperator
from datetime import datetime, timedelta

default_args = {
    'owner': 'rideshare',
    'depends_on_past': False,
    'start_date': datetime(2023, 1, 1),
    'email_on_failure': True,
    'email_on_retry': False,
    'retries': 1,
    'retry_delay': timedelta(minutes=5),
}

dag = DAG(
    'rideshare_etl',
    default_args=default_args,
    description='ETL DAG for RideShare Data Warehouse',
    schedule_interval=timedelta(minutes=30),
)

fetch_data = BashOperator(
    task_id='fetch_data',
    bash_command='python /opt/etl_scripts/fetch_data.py',
    dag=dag,
)

transform_data = BashOperator(
    task_id='transform_data',
    bash_command='python /opt/etl_scripts/transform_data.py',
    dag=dag,
)

load_data = BashOperator(
    task_id='load_data',
    bash_command='python /opt/etl_scripts/load_data.py',
    dag=dag,
)

fetch_data >> transform_data >> load_data
