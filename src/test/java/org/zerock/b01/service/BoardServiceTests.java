package org.zerock.b01.service;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.b01.dto.*;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Log4j2
@SpringBootTest
public class BoardServiceTests {

    @Autowired
    private BoardService boardService;


    @Test
    public void testResister() {
        log.info(boardService.getClass().getName());

        BoardDTO boardDTO = BoardDTO.builder()
                .title("Sample Data")
                .content("Sample Content Data")
                .writer("chae1-1111")
                .build();

        boardDTO.setFileNames(
                Arrays.asList(
                        UUID.randomUUID() + "_aaa.jpg",
                        UUID.randomUUID() + "_bbb.jpg",
                        UUID.randomUUID() + "_ccc.jpg"
                )
        );

        Long bno = boardService.register(boardDTO);

        log.info(bno);
    }

    @Test
    public void testReadOne() {

        BoardDTO boardDTO = boardService.readOne(101L);

        log.info(boardDTO);

        boardDTO.getFileNames().forEach(fileName -> {
            log.info(fileName);
        });
    }

    @Test
    public void testModify() {

        BoardDTO boardDTO = BoardDTO.builder()
                .bno(101L)
                .title("Modify Test Title")
                .content("Modify Test Content")
                .build();

        boardDTO.setFileNames(Arrays.asList(UUID.randomUUID() + "_zzz.jpg"));

        boardService.modify(boardDTO);
    }

    @Test
    public void testRemove() {
        boardService.remove(101L);

    }

    @Test
    public void testList() {
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .page(1)
                .size(10)
                .build();

        PageResponseDTO<BoardListAllDTO> responseDTO = boardService.listWithAll(pageRequestDTO);

        List<BoardListAllDTO> dtoList = responseDTO.getDtoList();

        dtoList.forEach(boardListAllDTO -> {
            log.info(boardListAllDTO.getBno() + ":" + boardListAllDTO.getTitle());

            if (boardListAllDTO.getBoardImages() != null) {
                for (BoardImageDTO boardImageDTO : boardListAllDTO.getBoardImages()) {
                    log.info(boardImageDTO);
                }
            }

            log.info("----------------------");
        });
    }
}
