import React, {useState} from 'react';
import {DesktopOutlined, FileOutlined, PieChartOutlined, TeamOutlined, UserOutlined,} from '@ant-design/icons';
import {Layout, Menu, theme, Select} from 'antd';
import TransactionsTable from "./transactionsTable";

const {Header, Content, Footer, Sider} = Layout;

function getItem(label, key, icon, children) {
    return {key, icon, children, label};
}

const items = [
    getItem('Option 1', '1', <PieChartOutlined/>),
    getItem('Option 2', '2', <DesktopOutlined/>),
    getItem('User', 'sub1', <UserOutlined/>, [
        getItem('Tom', '3'),
        getItem('Bill', '4'),
        getItem('Alex', '5'),
    ]),
    getItem('Team', 'sub2', <TeamOutlined/>, [getItem('Team 1', '6'), getItem('Team 2', '8')]),
    getItem('Files', '9', <FileOutlined/>),
];

const handleChange = (value) => {
    console.log(`selected ${value}`);
};


const App = () => {
    const [collapsed, setCollapsed] = useState(true);
    const {token: {colorBgContainer}} = theme.useToken();
    return (
        <Layout style={{minHeight: '100vh'}}>
            <Sider collapsible collapsed={collapsed} onCollapse={(value) => setCollapsed(value)}>
                <div style={{height: 32, margin: 16, background: 'rgba(255, 255, 255, 0.2)'}}/>
                <Menu theme="dark" defaultSelectedKeys={['1']} mode="inline" items={items}/>
            </Sider>
            <Layout className="site-layout">
                <Header style={{padding: 0, background: colorBgContainer}}/>
                <Content style={{margin: '0 16px'}}>
                    <Select defaultValue="current month"
                        style={{width: 200}}
                        onChange={handleChange}
                        options={[
                            {value: 'current month', label: 'Current Month'},
                            {value: 'current year', label: 'Current Year'},
                        ]}/>
                    <TransactionsTable/>
                </Content>
                <Footer style={{textAlign: 'center'}}>
                    Ant Design ©2018 Created by Ant UED
                </Footer>
            </Layout>
        </Layout>
    );
};
export default App;