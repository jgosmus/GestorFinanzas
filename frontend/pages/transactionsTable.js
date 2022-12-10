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


const TransactionsTable = () => {
    const [loading, setLoading] = useState(true);
    const [transactions, setTransactions] = useState([]);

    useEffect(() => {
        fetch('http://localhost:8080/api/v1/transactions').then(response => response.json()).then(data => {
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
    }, []);
    return <Table columns={columns} dataSource={transactions} loading={loading}/>;
};

export default TransactionsTable;