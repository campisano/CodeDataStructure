#ifndef DATASET_H_
#define DATASET_H_

template <typename T>
class Dataset
{
public:
    explicit Dataset()
    {}

    unsigned long getSize()
    {
        return m_data.size();
    }

    void setSize(unsigned long _size)
    {
        m_data.resize(_size);
    }

public:
    std::vector<T> m_data;
};

#endif
