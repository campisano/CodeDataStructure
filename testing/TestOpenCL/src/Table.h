#ifndef TABLE_H_
#define TABLE_H_

#include "Dataset.h"

template <typename T>
class Table
{
public:
    explicit Table():
        m_columns(0),
        m_rows(0)
    {
    }

    unsigned long getColumns()
    {
        return m_columns;
    }

    void setColumns(unsigned long _columns)
    {
        m_columns = _columns;
        m_data.resize(m_columns);
    }

    unsigned long getRows()
    {
        return m_rows;
    }

    void setRows(unsigned long _rows)
    {
        m_rows = _rows;

        for(unsigned long i = 0; i<m_data.size(); ++i)
        {
            m_data[i].setSize(m_rows);
        }
    }

public:
    std::vector< Dataset<T> > m_data;

private:
    unsigned long m_columns;
    unsigned long m_rows;
};

#endif
