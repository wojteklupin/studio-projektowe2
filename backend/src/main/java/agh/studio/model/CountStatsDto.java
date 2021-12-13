package agh.studio.model;

public class CountStatsDto {
    String label;
    Integer count;

    public CountStatsDto(String label, Integer count) {
        this.label = label;
        this.count = count;
    }

    public String getLabel() {
        return label;
    }

    public Integer getCount() {
        return count;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public static CountStatsDto fromIntegerCountProjection(IntegerCountProjection projection, int factor) {
        return new CountStatsDto((projection.getCategory() * factor) + " - " + ((projection.getCategory() + 1) * factor), projection.getCount());
    }
}
