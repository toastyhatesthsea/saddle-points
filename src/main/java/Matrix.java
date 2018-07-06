import java.util.*;

class Matrix {

    private List<List<Integer>> aValues;

    Matrix(List<List<Integer>> values) {

        this.aValues = values;
    }

    Set<MatrixCoordinate> getSaddlePoints() {

        Set<MatrixCoordinate> answer = new HashSet<>();
        if (this.aValues.isEmpty())
        {
            return answer;
        }

        List<List<Integer>> sortedLists = sortedList(aValues);
        int row = 0;

        for (List aList : aValues)
        {
            int largestInRow = sortedLists.get(row).get(sortedLists.get(row).size() - 1);
            List<Integer> locations = locationOfLargest(aList, largestInRow);

            for (Integer columnLocation : locations)
            {
                boolean smallestInColumn = isSmallest(aValues, largestInRow, columnLocation);
                if (smallestInColumn)
                {
                    answer.add(new MatrixCoordinate(row, columnLocation));
                }
            }
            row++;
        }
        return answer;
    }

    /**
     * Finds the column locations of the largest value in a row
     * @param aList List<Integer></Integer>
     * @param largestValue int
     * @return List<Integer></Integer>
     */
    private List<Integer> locationOfLargest(List<Integer> aList, int largestValue)
    {
        List<Integer> answer = new ArrayList<>();

        int location = 0;
        for (Integer aNum : aList)
        {
            if (aNum == largestValue)
            {
                answer.add(location);
            }
            location++;
        }
        return answer;
    }

    /**
     * Sorts the Entire Matrix and returns a List of Lists
     * @param aListOfLists List<List<Integer></Integer>
     * @return List<List<Integer></Integer>
     */
    private List<List<Integer>> sortedList(List<List<Integer>> aListOfLists)
    {
        List<List<Integer>> aSortedList = new ArrayList<>();

        int count = 0;
        for (List aList : aListOfLists)
        {
            List<Integer> aNewList = new ArrayList<>();

            for (Object aInteger : aList)
            {
                aNewList.add((Integer)aInteger);
            }
            aSortedList.add(aNewList);

            aSortedList.get(count).sort(null);
            count++;
        }
        return aSortedList;
    }

    /**
     * Checks if the value is the largest in the designated column
     * @param aList List<List<Integer></Integer>
     * @param value int
     * @param column int
     * @return boolean
     */
    private boolean isSmallest(List<List<Integer>> aList, int value, int column)
    {
        boolean answer = true;

        for (List someList : aList)
        {
            int columnValue = (Integer)someList.get(column);

            if (value > columnValue)
            {
                return false;
            }
        }
        return answer;
    }
}

