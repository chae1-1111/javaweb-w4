package org.zerock.b01.repository;

import lombok.Cleanup;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.b01.domain.Board;
import org.zerock.b01.domain.BoardImage;
import org.zerock.b01.dto.BoardListAllDTO;
import org.zerock.b01.dto.BoardListReplyCountDTO;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class BoardRepositoryTests {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private ReplyRepository replyRepository;

    @Test
    @Transactional
    @Commit
    public void testRemoveAll() {

        Long bno = 2L;

        replyRepository.deleteByBoard_Bno(bno);

        boardRepository.deleteById(bno);
    }

    @Test
    public void testInsertWithImages() {

        Board board = Board.builder()
                .title("Image Test")
                .content("첨부파일 테스트")
                .writer("tester")
                .build();

        for (int i=0; i<3; i++) {
            board.addImage(UUID.randomUUID().toString(), "file"+i+".jpg");

        }

        boardRepository.save(board);
    }

    @Test
    public void testReadWithImages() {

        Optional<Board> result = boardRepository.findByIdWithImages(2L);

        Board board = result.orElseThrow();

        log.info(board);
        log.info("--------------------");
        for (BoardImage boardImage : board.getImageSet()) {
            log.info(boardImage);
        }
    }

    @Test
    public void testInsertMany() {

        for (int i=0; i<100; i++) {

            Board board = Board.builder()
                    .title("Title_" + i)
                    .content("Content_"+i)
                    .writer("writer_" + i%10)
                    .build();

            for (int j=0; j<3; j++) {

                if(i%5 == 0) {
                    continue;
                }

                board.addImage(UUID.randomUUID().toString(), i+"file"+j+".jpg");
            }
            boardRepository.save(board);
        }
    }

    @Test
    @Transactional
    public void testSearchImageReplyCount() {

        Pageable pageable = PageRequest.of(0,10, Sort.by("bno").descending());

//        boardRepository.searchWithAll(null, null, pageable);

        Page<BoardListAllDTO> result = boardRepository.searchWithAll(null, null, pageable);

        log.info("------------------------");
        log.info(result.getTotalElements());

        result.getContent().forEach(boardListAllDTO -> log.info(boardListAllDTO));
    }



//    @Test
//    public void testInsert() {
//        IntStream.rangeClosed(1,100).forEach(i -> {
//            Board board = Board.builder()
//                    .title("title" + i)
//                    .content("content" + i)
//                    .writer("user" + i%10)
//                    .build();
//
//            Board result = boardRepository.save(board);
//            log.info("BNO: " + result.getBno());
//        });
//    }
//
//    @Test
//    public void testSelect() {
//        Long bno = 150L;
//
//        Optional<Board> result = boardRepository.findById(bno);
//
//        Board board = result.orElseThrow();
//
//        log.info(board);
//    }
//
//    @Test
//    public void testUpdate() {
//        Long bno = 150L;
//
//        Optional<Board> result = boardRepository.findById(bno);
//
//        Board board = result.orElseThrow();
//
//        board.change("new Title", "new Content(update test)");
//
//        boardRepository.save(board);
//    }
//
//    @Test
//    public void testDelete() {
//        Long bno = 160L;
//
//        boardRepository.deleteById(bno);
//    }
//
//    @Test
//    public void testPaging() {
//
//        // 1 page order by bno desc
//        Pageable pageable = PageRequest.of(0,10, Sort.by("bno").descending());
//
//        Page<Board> result = boardRepository.findAll(pageable);
//
//        log.info("total count: " + result.getTotalElements());
//        log.info("total pages: " + result.getTotalPages());
//        log.info("page number: " + result.getNumber());
//        log.info("page size: " + result.getSize());
//    }
//
//    @Test
//    public void testSearch1() {
//
//        Pageable pageable = PageRequest.of(1,10, Sort.by("bno").descending());
//
//        log.info(pageable);
//
//        log.info(boardRepository.search1(pageable));
//    }
//
//    @Test
//    public void testSearchAll() {
//
//        String[] types = {"t", "c", "w"};
//
//        String keyword = "1";
//
//        Pageable pageable = PageRequest.of(0,10,Sort.by("bno").descending());
//
//        Page<Board> result = boardRepository.searchAll(types, keyword, pageable);
//
//    }
//
//    @Test
//    public void testSearchAll2() {
//
//        String[] types = {"t", "c", "w"};
//
//        String keyword = "1";
//
//        Pageable pageable = PageRequest.of(9,10,Sort.by("bno").descending());
//
//        Page<Board> result = boardRepository.searchAll(types, keyword, pageable);
//
//        log.info(result.getTotalPages());
//
//        log.info(result.getSize());
//
//        log.info(result.getNumber());
//
//        log.info(result.hasPrevious() + ": " + result.hasNext());
//
//        result.getContent().forEach(board -> log.info(board));
//    }
//
//    @Test
//    public void testSearchReplyCount() {
//
//        String[] types = {"t","c","w"};
//
//        String keyword = "s";
//
//        Pageable pageable = PageRequest.of(0, 10, Sort.by("bno").descending());
//
//        Page<BoardListReplyCountDTO> result = boardRepository.searchWithReplyCount(types, keyword, pageable);
//
//        log.info(result.getTotalPages());
//        log.info(result.getSize());
//        log.info(result.getNumber());
//        log.info(result.hasPrevious() + ":" + result.hasNext());
//
//        result.getContent().forEach(board -> log.info(board));
//    }
}
