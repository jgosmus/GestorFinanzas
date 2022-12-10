import React, {useEffect, useState} from 'react';
import {Table} from "antd";

const columns = [
    {title: 'Transactions', dataIndex: 'transactions', width: 100, fixed: 'left', children: [
        {title: 'Date', dataIndex: 'date', width: '8%'},
        {title: 'Concept', dataIndex: 'concept', width: '50%'},
        {title: 'Amount', dataIndex: 'amount', width: '8%'},
        {title: 'Category', dataIndex: 'category', width: '8%'},
        {title: 'Note', dataIndex: 'note'}
        ]
    }
    ];

const calculateDateRange = (dateRange) => {
    const today = new Date();
    const year = today.getFullYear();
    const month = today.getMonth() + 1;
    const day = today.getDate();

    switch (dateRange) {
        case 'current month':
            return `${year}-${month}-01`;
        case 'current year':
            return `${year}-01-01`;
        default:
            return `${year}-${month}-${day}`;
    }
}

const TransactionsTable = ({dateRange}) => {
    const [loading, setLoading] = useState(true);
    const [transactions, setTransactions] = useState([]);

    useEffect(() => {
        fetch('http://localhost:8080/api/v1/transactions/' + calculateDateRange(dateRange))
            .then(response => response.json()).then(data => {
            setLoading(false);
            const transactions = data.map((transaction) => {
                return {
                    key: transaction.id,
                    date: transaction.valueDate,
                    concept: transaction.concept,
                    amount: transaction.transactionAmount.amount,
                    category: "Unasigned",
                    note: ""
                }
            });

            setTransactions(transactions);
        });
    }, [dateRange]);
    return <Table columns={columns} dataSource={transactions} loading={loading}/>;
};

export default TransactionsTable;