package dao;

import model.TagFinance;

import java.util.List;

public interface TagFinanceDAO {
    void insertTagFinance(TagFinance tagFinance);

    List<TagFinance> getTagFinanceList();

    void updateTagFinance(TagFinance tagFinance, int id);

    void deleteTagFinance(int id);

}
